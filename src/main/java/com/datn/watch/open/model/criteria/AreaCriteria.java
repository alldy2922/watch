package com.datn.watch.open.model.criteria;

import com.pet.market.api.common.model.criteria.SearchCriteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaCriteria extends SearchCriteria {

    private String districtId;

    private String provinceId;

}
