package com.datn.watch.shipping.vnPost;

import com.pet.market.api.interlock.shipping.model.vnpost.*;
import com.pet.market.api.shipping.model.dto.VnPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VnPostService {
  String LOGIN_VNPOST = "/customer-partner/GetAccessToken";

  String CREATE_DOMESTIC_REQUEST = "/customer-partner/CreateOrder";

  String UPDATE_DOMESTIC_REQUEST = "/customer-partner/orderCorrection";

  String CALCULATE_FEE = "/customer-partner/ServicesCharge";

  String CANCEL_ORDER = "/customer-partner/orderCancel";

  String GET_ALL_PROVINCE = "/customer-partner/getAllProvince";

  String GET_ALL_DISTINCT = "/customer-partner/getAllDistrict";

  String GET_ALL_COMMUNE = "/customer-partner/getAllCommune";

  Object createDomesticOrder(OrderCreationReq req);

  Response updateDomesticOrder(UpdateOrderReq req);

  Object calculateFee(FeeOrderReq req);

  Object cancelOrder(CancelOrderReq req);

  List<VnPostDTO.Spdv> getAllSpdv();

  List<VnPostDTO.Gtgt> getAllGtgt(String group);

  List<VnPostDTO.Prop> getAllProp(String gtgt);

  List<VnPostArea.Province> listAllProvince();

  List<VnPostArea.District> listAllDistinct();

  List<VnPostArea.Commune> listAllCommune();

}
