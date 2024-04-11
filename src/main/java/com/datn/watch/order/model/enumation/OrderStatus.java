package com.datn.watch.order.model.enumation;

public enum OrderStatus {

  CREATE(0), CONFIRMED(1), REJECTED(2), PACKING(3), READY_TO_SHIP(4), SHIPPING(5), COMPLETED(6), CANCELLED(7), REQUEST_BACK(8), BACK(9);

  public int value;

  OrderStatus(int value) {
    this.value = value;
  }

  public static OrderStatus getByString(String source) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.name().equals(source)) return status;
    }
    return OrderStatus.CREATE;
  }

  public static OrderStatus getByInt(int source) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.value == source) return status;
    }
    return OrderStatus.CREATE;
  }

}
