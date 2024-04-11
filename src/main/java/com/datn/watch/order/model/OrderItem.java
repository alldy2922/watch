package com.datn.watch.order.model;

import lombok.Data;

@Data
public class OrderItem {

  private Long id;

  private Long productId;

  private String name;

  private Long orderId;

  private Long price;

  private Integer quantity;

}
