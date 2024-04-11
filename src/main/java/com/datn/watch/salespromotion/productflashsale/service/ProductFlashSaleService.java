package com.datn.watch.salespromotion.productflashsale.service;

import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.salespromotion.productflashsale.model.dto.ProductFlashSaleDto;
import com.pet.market.api.salespromotion.productflashsale.repository.ProductFlashSaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tobi
 */
@Service
public class ProductFlashSaleService {
    private final ProductFlashSaleRepository mapper;

    public ProductFlashSaleService(ProductFlashSaleRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(ProductFlashSaleDto.ListReq req) {
        if (req.getEnabledPage()) {
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), mapper.count(req), ModelMapperUtils.mapAll(mapper.getAll(req), ProductFlashSaleDto.Response.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), ProductFlashSaleDto.Response.class));
        }
    }

    public Object get(Long id) {
        return mapper.getById(id).map(value -> ModelMapperUtils.map(value, ProductFlashSaleDto.Response.class)).orElse(null);
    }

    @Transactional
    public void save(ProductFlashSaleDto.Request req) {
        if (req.getId() != 0L){
            mapper.update(req.toEntity());
        }else{
            mapper.save(req.toEntity());
        }
    }

    @Transactional
    public void update(ProductFlashSaleDto.Request req) {
        mapper.update(req.toEntity());
    }

    public void updateStatus(ProductFlashSaleDto.StatusReq req) {
        mapper.updateStatus(req);
    }

    public void delete(Long id) {
        mapper.delete(id);
    }

    public void deletes(ProductFlashSaleDto.DeleteReq req) {
        mapper.deletes(req);
    }

}
