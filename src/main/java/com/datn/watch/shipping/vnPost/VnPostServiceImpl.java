package com.datn.watch.shipping.vnPost;

import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.interlock.shipping.model.vnpost.*;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea.Commune;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea.District;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea.Province;
import com.pet.market.api.interlock.shipping.model.vnpost.authen.LoginReq;
import com.pet.market.api.interlock.shipping.model.vnpost.authen.LoginRes;
import com.pet.market.api.shipping.model.DeliveryProvider;
import com.pet.market.api.shipping.model.Gtgt;
import com.pet.market.api.shipping.model.Prop;
import com.pet.market.api.shipping.model.Spdv;
import com.pet.market.api.shipping.model.dto.VnPostDTO;
import com.pet.market.api.shipping.repository.ShippingRepository;
import com.pet.market.api.shipping.repository.VnPostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;

@Service
public class VnPostServiceImpl implements VnPostService {
  @Value("${interlock.shipping.vn-post.url}")
  private String url;
  private final String ENV_PROD = "PROD";
  private final String ENV_DEV = "DEV";
  private String CODE = "VNPOST";

  @Value("${interlock.shipping.vn-post.env}")
  private String env;

  private String customerCode;
  private final ShippingRepository shippingRepository;

  private final VnPostRepository vnPostRepository;

  private final WebClient client;

  public VnPostServiceImpl(ShippingRepository shippingRepository, VnPostRepository vnPostRepository) {
    this.shippingRepository = shippingRepository;
    this.vnPostRepository = vnPostRepository;

    Optional<DeliveryProvider> vnPost = shippingRepository.findByCode(CODE);

    this.customerCode = vnPost.isPresent() ? vnPost.get().getCustomerCode() : "";

    this.client = WebClient
        .builder()
        .baseUrl(url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("token", vnPost.isPresent() ? vnPost.get().getApiKey() : "")
        .codecs(codec -> codec.defaultCodecs().maxInMemorySize(5000 * 1024))
        .build();
  }

  @Override
  public Object createDomesticOrder(OrderCreationReq req) {
    req.setCustomerCode(customerCode);
    return callApi(CREATE_DOMESTIC_REQUEST, "post", req, new OrderCreationRes());
  }

  @Override
  public Response updateDomesticOrder(UpdateOrderReq req) {
    return client.post().uri(UPDATE_DOMESTIC_REQUEST)
        .bodyValue(req).retrieve().bodyToMono(Response.class).block();
  }

  @Override
  public Object calculateFee(FeeOrderReq req) {
    req.setCustomerCode(customerCode);
    return callApi(CALCULATE_FEE, "post", req, new FeeOrderRes[1]);
  }

  @Override
  public Object cancelOrder(CancelOrderReq req) {
    return callApi(CANCEL_ORDER, "post",req, new Response[1]);
  }

  @Override
  public List<VnPostDTO.Spdv> getAllSpdv() {
    List<Spdv> result = vnPostRepository.getAllSpdv();
    return result.stream().map(VnPostDTO.Spdv::toRes).toList();
  }

  @Override
  public List<VnPostDTO.Gtgt> getAllGtgt(String spdv) {
    List<Gtgt> result = vnPostRepository.getAllGtgt(spdv);
    return result.stream().map(VnPostDTO.Gtgt::toRes).toList();
  }

  @Override
  public List<VnPostDTO.Prop> getAllProp(String gtgt) {
    List<Prop> result = vnPostRepository.getAllProp(gtgt);
    return result.stream().map(VnPostDTO.Prop::toRes).toList();
  }

  @Override
  public List<Province> listAllProvince() {
    return client.get().uri(GET_ALL_PROVINCE).retrieve().bodyToMono(new ParameterizedTypeReference<List<Province>>() {}).block();
  }

  @Override
  public List<District> listAllDistinct() {
    return client.get().uri(GET_ALL_DISTINCT).retrieve().bodyToMono(new ParameterizedTypeReference<List<District>>() {}).block();
  }

  @Override
  public List<Commune> listAllCommune() {
    return client.get().uri(GET_ALL_COMMUNE).retrieve().bodyToMono(new ParameterizedTypeReference<List<Commune>>() {}).block();
  }

  public Object callApi(String path, String methob, Object body, Object classMap){
    Boolean isLogin = false;
    int times = 0;
    Object res = null;
    do {
      try {
        String token = getApiKey(1);
        if("post".equalsIgnoreCase(methob)) {
          res = client.post().uri(url + path)
                  .headers(httpHeaders -> {
                    httpHeaders.set("token", token);
                  })
                  .bodyValue(body)
                  .retrieve()
                  .bodyToMono(classMap.getClass())
                  .block();
        }else if("get".equalsIgnoreCase(methob)){
          res = client.get().uri(url + path)
                  .headers(httpHeaders -> {
                    httpHeaders.set("token", token);
                  })
                  .retrieve()
                  .bodyToMono(classMap.getClass())
                  .block();
        }
        isLogin = true;
      } catch (WebClientResponseException e) {
        if (e.getStatusCode().value() == 401) {
          if(times == 0){
            times ++;
            getApiKey(2);
          }
          else{
            throw new PetMarketApiException("Token vnpost không chính xác");
          }
        }else if(e.getStatusCode().value() == 422){
          throw new PetMarketApiException("Lỗi nghiệp vụ");
        }else {
          throw new PetMarketApiException("Lỗi nghiệp vụ");
        }
      } catch (Exception e) {
        throw new PetMarketApiException("Lỗi hệ thống");
      }
    }
    while (!isLogin & times == 1);
    return res;
  }
  public LoginRes login(LoginReq loginReq) {
    LoginRes loginRes = new LoginRes();
    try {
      loginRes = client.post().uri(url + LOGIN_VNPOST)
              .bodyValue(loginReq)
              .retrieve().bodyToMono(LoginRes.class).block();
      return loginRes;
    }
    catch (Exception e){
      return loginRes;
    }
  }

  @Transactional
  public String getApiKey(int type) {
    String apiKey = "";
    Optional<DeliveryProvider> unit = shippingRepository.findByCode(CODE);
      if(type == 1){
      if (unit.isPresent()) {
        if (ENV_DEV.equalsIgnoreCase(env)) {
          apiKey = unit.get().getApiKeyDev();
        } else {
          if (ENV_PROD.equalsIgnoreCase(env)) {
            apiKey = unit.get().getApiKey();
          }
        }
      }
    }else{
      if(type == 2) {
        if (unit.isPresent()) {
          LoginReq loginReq = new LoginReq(unit.get().getUsername(), unit.get().getPassword(), unit.get().getCustomerCode());
          LoginRes res = login(loginReq);
          if (res.getSuccess()) {
            DeliveryProvider ship = new DeliveryProvider();
            ship.setId(unit.get().getId());
            if (ENV_DEV.equalsIgnoreCase(env)) {
              ship.setApiKeyDev(res.getToken());
            } else {
              if (ENV_PROD.equalsIgnoreCase(env)) {
                ship.setApiKey(res.getToken());
              }
            }
            shippingRepository.update(ship);
            apiKey = res.getToken();
          } else {
            apiKey = "";
          }
        }
      }
    }
    return apiKey;
  }
}
