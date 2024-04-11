package com.datn.watch.shipping.model.criteria;

import com.pet.market.api.common.utils.StringUtils;
import com.pet.market.api.common.utils.paging.Page;
import lombok.Getter;

@Getter
public class ShippingCriteria extends Page {

  private String quickSearch;

  private Boolean status;

  public void setQuickSearch(String quickSearch) {
    if (StringUtils.isNullOrEmpty(quickSearch)) {
      this.quickSearch = quickSearch;
    } else {
      this.quickSearch = "%" + quickSearch.toLowerCase() + "%";
    }
  }

  public void setActive(Boolean active) {
    status = active;
  }
}
