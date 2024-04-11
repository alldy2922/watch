package com.datn.watch.customer.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AuditableEntity {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String province;
    private String district;
    private String commune;
    private String address;
    private Integer gender;
    private String birthDay;
    private Integer type;
    private String taxCode;
    private String email;
    private String facebook;
    private String customerGroup;
    private String note;
    private Integer status;
}
