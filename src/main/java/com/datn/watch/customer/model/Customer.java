package com.datn.watch.customer.model;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AuditableEntity {

  public Customer(String fullName, String phoneNumber, String province, String district,
      String commune, String address) {
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.province = province;
    this.district = district;
    this.commune = commune;
    this.address = address;
  }

  private String id;

  private String fullName;

  private String gender;

  private String phoneNumber;

  private String province;

  private String district;

  private String commune;

  private String address;

  private Boolean status;
}
