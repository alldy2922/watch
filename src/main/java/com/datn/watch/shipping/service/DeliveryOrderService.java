package com.datn.watch.shipping.service;

import com.pet.market.api.interlock.shipping.vnPost.VnPostService;
import com.pet.market.api.shipping.repository.ShippingRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderService {

  private final VnPostService vnPostService;

  private final ShippingRepository shippingRepository;

  public DeliveryOrderService(VnPostService vnPostService, ShippingRepository shippingRepository) {
    this.vnPostService = vnPostService;
    this.shippingRepository = shippingRepository;
  }

  public boolean createDeliveryOrder(String orderType) {
    if ("Vnpost".equals(orderType)) {
      //vnPostService.createDomesticOrder();
    }
    return true;
  }
  
}
