package com.datn.watch.order.model;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order extends AuditableEntity {

  private Long id;

  private String customerId;

  private Long price;

  private Long discountPrice;

  private Boolean isPaid;

  private String paymentType;

  private String shippingUnit;

  private String shippingCode;

  private String marketPlace;

  private String receiver;

  private LocalDateTime dayReception;

  private LocalDateTime deliveryDateShippingUnit;

  private Integer status;

  private List<OrderItem> items;

}
