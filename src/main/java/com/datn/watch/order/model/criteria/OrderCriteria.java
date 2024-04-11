package com.datn.watch.order.model.criteria;

import com.pet.market.api.common.model.criteria.SearchCriteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCriteria extends SearchCriteria {

  private String createdBy;

  private List<Integer> status;

  private Boolean isPaid;

  private String marketPlace;

  private String startReception;

  private String endReception;

}
