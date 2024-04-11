package com.datn.watch.order.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pet.market.api.order.model.Order;
import com.pet.market.api.order.model.OrderItem;
import com.pet.market.api.order.model.OrderShipping;
import com.pet.market.api.order.model.enumation.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

  @Data
  public static class CreateOrderReq {

    @NotBlank
    private String fullName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String paymentType;

    private String country = "VN";

    private String province;

    private String district;

    private String commune;

    private String detail;

    private String promotion;

    private String paymentMethod;

    @NotEmpty
    private List<OrderReq.Item> items;

  }

  @Data
  public static class OrderReq {

    @NotBlank
    private String paymentType;

    @NotNull
    private Customer receiver;

    private Address address;

    private Receiver repReceiver;

    private Company taxCompany;

    private String promotion;

    private String paymentMethod;

    @NotEmpty
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {

      @NotNull
      private Long productId;

      @NotNull
      private Integer quantity;
    }

    @Getter
    @Setter
    public static class Customer {

      @NotBlank
      private Boolean isMale;

      @NotBlank
      private String name;

      @NotBlank
      private String phoneNumber;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {

      private String country = "VN";

      private String province;

      private String district;

      private String commune;

      private String detail;

    }

    @Getter
    @Setter
    public static class Receiver {

      private Boolean isMale;

      private String name;

      private String phoneNumber;

    }

    @Getter
    @Setter
    public static class Company {

      private String name;

      private String address;

      private String taxNumber;

    }


  }

  @Data
  public static class OrderRes {

    private Long id;

    private String customerId;

    private Long price;

    private Long discountPrice;

    private Boolean isPaid;

    private String paymentType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shippingUnit;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shippingCode;

    private String marketPlace;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Shipping shipping;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime dayReception;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime deliveryDateShippingUnit;

    private String status;

    private Long cost;

    private String createdBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {

      @NotNull
      private String id;

      @NotNull
      private Long productId;

      private String name;

      @NotNull
      private Integer quantity;

      @NotNull
      private Long price;

      private Long discount;
    }

    @Getter
    @Setter
    public static class Shipping {

      private String idCustomer;

      private String fullName;

      private String phoneNumber;

      private String province;

      private String provinceName;

      private String district;

      private String districtName;

      private String commune;

      private String communeName;

      private String address;

      public static Shipping toRes(OrderShipping source) {
        Shipping res = new Shipping();
        BeanUtils.copyProperties(source, res);
        return res;
      }

    }

    public static OrderRes toRes(Order source) {
      OrderRes res = new OrderRes();
      BeanUtils.copyProperties(source, res);
      res.setStatus(OrderStatus.getByInt(source.getStatus()).name());
      res.setCost(source.getPrice() - source.getDiscountPrice());
      return res;
    }

    public static Item toItemRes(OrderItem source) {
      Item res = new Item();
      BeanUtils.copyProperties(source, res);
      return res;
    }
  }

  public static List<OrderRes> toOrders(List<Order> orders) {
    return orders.stream().map(OrderRes::toRes).toList();
  }

}
