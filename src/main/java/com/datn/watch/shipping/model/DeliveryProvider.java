package com.datn.watch.shipping.model;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;

@Data
public class DeliveryProvider extends AuditableEntity {

  private Long id;

  private String name;

  private String apiKey;

  private String apiKeyDev;

  private String customerCode;

  private String username;

  private String password;

  private Boolean status;

  private String image;

}
