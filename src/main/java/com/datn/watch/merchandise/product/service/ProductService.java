package com.datn.watch.merchandise.product.service;

import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.merchandise.product.model.dto.ProductDto;
import com.pet.market.api.merchandise.product.model.entity.Product;
import com.pet.market.api.merchandise.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tobi
 */
@Service
public class ProductService {

    private final ProductRepository mapper;

    public ProductService(ProductRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(ProductDto.ListReq req) {
        if (req.getEnabledPage()) {
            int totalRecords = mapper.count(req);
            List<Product> list = mapper.getAll(req);
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, ModelMapperUtils.mapAll(list, ProductDto.Response.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), ProductDto.Response.class));
        }
    }

    public Object get(Long id) {
        return mapper.getById(id).map(value -> ModelMapperUtils.map(value, ProductDto.DetailResponse.class)).orElse(null);
    }

    public void save(ProductDto.Request req) {
        mapper.save(req.toEntity());
    }

    public void update(ProductDto.Request req) {
        mapper.update(req.toEntity());
    }

    public void delete(ProductDto.DeleteReq req) {
            mapper.deletes(req);
    }

    public void updateStatus(ProductDto.StatusReq req) {
        mapper.updateStatus(req);
    }
}
