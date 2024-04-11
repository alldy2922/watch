package com.datn.watch.order.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.market.api.common.exception.DataNotFoundException;
import com.pet.market.api.common.exception.PromotionException;
import com.pet.market.api.merchandise.product.model.entity.Product;
import com.pet.market.api.merchandise.product.repository.ProductRepository;
import com.pet.market.api.order.model.Order;
import com.pet.market.api.order.model.OrderItem;
import com.pet.market.api.order.model.OrderShipping;
import com.pet.market.api.order.model.criteria.OrderCriteria;
import com.pet.market.api.order.model.dto.OrderDTO;
import com.pet.market.api.order.model.dto.OrderDTO.OrderReq;
import com.pet.market.api.order.model.dto.OrderDTO.OrderReq.Item;
import com.pet.market.api.order.model.dto.OrderDTO.OrderRes;
import com.pet.market.api.order.model.enumation.MarketPlace;
import com.pet.market.api.order.model.enumation.OrderStatus;
import com.pet.market.api.order.repository.OrderDetailRepository;
import com.pet.market.api.order.repository.OrderRepository;
import com.pet.market.api.order.repository.OrderShippingRepository;
import com.pet.market.api.salespromotion.promotion.model.entity.Promotion;
import com.pet.market.api.salespromotion.promotion.utils.PromotionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

  private final OrderRepository repository;

  private final OrderShippingRepository shippingRepository;

  private final ProductRepository productRepository;

  private final OrderDetailRepository detailRepository;

  private final OrderDetailsService detailsService;

  private final PromotionUtils promotionUtils;

  private final ObjectMapper mapper = new ObjectMapper();

  public OrderService(OrderRepository repository,
                      OrderShippingRepository shippingRepository, ProductRepository productRepository, OrderDetailRepository detailRepository,
                      OrderDetailsService detailsService, PromotionUtils promotionUtils) {
    this.repository = repository;
    this.shippingRepository = shippingRepository;
    this.productRepository = productRepository;
    this.detailRepository = detailRepository;
    this.detailsService = detailsService;
      this.promotionUtils = promotionUtils;
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public int count(OrderCriteria criteria) {
    return repository.count(criteria);
  }

  public List<OrderRes> list(OrderCriteria criteria) {
    List<Order> orders = repository.getAll(criteria);
    return OrderDTO.toOrders(orders);
  }

  public OrderRes get(long orderId) {
    OrderRes res = repository.getOrder(orderId).map(OrderRes::toRes).orElseThrow(() -> new DataNotFoundException("Khong tim thay thong tin dat hang"));
    res.setItems(detailRepository.getAll(res.getId()).stream().map(OrderRes::toItemRes).toList());

    Optional<OrderShipping> shipping = shippingRepository.get(orderId);
    shipping.ifPresent(orderShipping -> res.setShipping(OrderRes.Shipping.toRes(orderShipping)));
    return res;
  }

  @Transactional
  public long create(OrderDTO.OrderReq req) {

    Order order = handleOrder(req, MarketPlace.WEB.name());
//    TODO Handle flash sale & promotion
    calculateWithPromotion(order, req);
    long orderId = repository.save(order);
    order.getItems().forEach(item -> item.setOrderId(orderId)); //Set order id
    shippingRepository.save(req.getReceiver(), req.getAddress(), orderId);
    promotionUtils.promotionIsUse(Long.valueOf(req.getPromotion()), order.getCustomerId(), orderId);
    detailsService.create(order.getItems());
    return orderId;
  }

  public void update(long id, Map<String, Object> params) {
    if (params.get("status") != null) {
      params.remove("status");
    }
    Order order = mapper.convertValue(params, Order.class);
    OrderShipping shipping = mapper.convertValue(params, OrderShipping.class);
    order.setId(id);
    shipping.setId(id);
    repository.update(order);
    shippingRepository.update(shipping);
  }

  public void recepict(long id, boolean confirm) {
    Optional<Order> orderOptional = repository.getOrder(id);
    if (orderOptional.isPresent()) {
      Order order = orderOptional.get();
      if (OrderStatus.CREATE.value == order.getStatus()) {
        Order params = new Order();
        params.setId(id);
        params.setStatus(confirm ? OrderStatus.CONFIRMED.value : OrderStatus.REJECTED.value);
        repository.update(params);
      }
    }
  }

  /**
   * Xử ly thông tin order
   * @param req rquest order
   * @param marketPlace thong tin market place: web, tiktok, shopee
   * @return thông tin order
   */
  private Order handleOrder(OrderReq req, String marketPlace) {
    long price = 0;
    long discountPrice = 0;
    Order order = new Order();
    List<OrderItem> items = new ArrayList<>();
    List<Long> ids = req.getItems().stream().map(Item::getProductId).toList();
    List<Product> products = productRepository.getProductByIds(ids);
    Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    for (Item item : req.getItems()) {
      Product product = productMap.get(item.getProductId());
      if ( Objects.nonNull(product)) {
        price += product.getPrice();
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(product.getId());
        orderItem.setPrice(product.getPrice());
        orderItem.setQuantity(item.getQuantity());
        items.add(orderItem);
      }
    }
    order.setCustomerId(req.getReceiver().getPhoneNumber());
    order.setItems(items);
    order.setPrice(price);
    order.setPaymentType(req.getPaymentType());
    order.setMarketPlace(marketPlace);
    order.setDiscountPrice(discountPrice);
    return order;
  };
  public void calculateWithPromotion(Order order, OrderReq req) {
    try {
      Promotion promotion = promotionUtils.getById(Long.valueOf(req.getPromotion()));
      order.setPrice(order.getPrice() - promotion.getValue());
      order.setDiscountPrice(promotion.getValue());
    } catch (Exception e) {
      throw new PromotionException();
    }
  }
}
