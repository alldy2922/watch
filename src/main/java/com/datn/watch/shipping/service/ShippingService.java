package com.datn.watch.shipping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.market.api.common.exception.DataNotFoundException;
import com.pet.market.api.shipping.model.DeliveryProvider;
import com.pet.market.api.shipping.model.criteria.ShippingCriteria;
import com.pet.market.api.shipping.model.dto.ShippingProviderDTO;
import com.pet.market.api.shipping.model.dto.ShippingProviderDTO.DeliveryProviderRes;
import com.pet.market.api.shipping.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShippingService {

  private final ShippingRepository repository;

  private final ObjectMapper objectMapper;

  public ShippingService(ShippingRepository repository, ObjectMapper objectMapper) {
    this.repository = repository;
    this.objectMapper = objectMapper;
  }

  public int count(ShippingCriteria criteria) {
    return repository.count(criteria);
  }

  public List<DeliveryProviderRes> list(ShippingCriteria criteria) {
    return ShippingProviderDTO.toDeliveryRes(repository.getAll(criteria));
  }

  public DeliveryProviderRes get(long id) {
    return repository.get(id).map(DeliveryProviderRes::toRes).orElseThrow(() -> new DataNotFoundException("Khong tim thay don vi van chuyen"));
  }

  public long create(ShippingProviderDTO.DeliveryProviderReq req) {
    DeliveryProvider provider = req.toEntity();
    repository.save(provider);
    return provider.getId();
  }

  public void update(long id, Map<String, Object> params) {
    DeliveryProvider provider = objectMapper.convertValue(params, DeliveryProvider.class);
    provider.setId(id);
    repository.update(provider);
  }

}
