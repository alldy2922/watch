package com.datn.watch.customer.model;

import com.pet.market.api.common.model.criteria.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLisReq extends SearchCriteria {
    private String phoneNumber;

    private Boolean active;
}
