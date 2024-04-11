package com.datn.watch.shipping.model.dto;

import com.pet.market.api.shipping.model.DeliveryProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ShippingProviderDTO {

  @Getter
  @Setter
  public static class DeliveryProviderReq {

    @NotBlank
    private String name;

    private String apiKey;

    private String apiDevKey;

    @NotNull
    private Boolean status;

    public DeliveryProvider toEntity() {
      DeliveryProvider entity = new DeliveryProvider();
      BeanUtils.copyProperties(this, entity);
      return entity;
    }

  }

  @Getter
  @Setter
  public static class DeliveryProviderRes {

    private Long id;

    private String name;

    private String apiKey;

    private String apiDevKey;

    private Boolean status;

    private LocalDateTime createdDate;

    public static DeliveryProviderRes toRes(DeliveryProvider source) {
      DeliveryProviderRes res = new DeliveryProviderRes();
      BeanUtils.copyProperties(source, res);
      return res;
    }
  }

  public static List<DeliveryProviderRes> toDeliveryRes(List<DeliveryProvider> source) {
    return source.stream().map(DeliveryProviderRes::toRes).toList();
  }

}
