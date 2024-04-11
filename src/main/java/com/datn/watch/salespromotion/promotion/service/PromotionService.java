package com.datn.watch.salespromotion.promotion.service;

import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.salespromotion.promotion.model.dto.PromotionDto;
import com.pet.market.api.salespromotion.promotion.repository.PromotionRepository;
import org.springframework.stereotype.Service;

/**
 * @author tobi
 */
@Service
public class PromotionService {
    private final PromotionRepository mapper;

    public PromotionService(PromotionRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(PromotionDto.ListReq req) {
        if (req.getEnabledPage()) {
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), mapper.count(req), ModelMapperUtils.mapAll(mapper.getAll(req), PromotionDto.Response.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), PromotionDto.Response.class));
        }
    }

    public Object get(Long id) {
        return mapper.getById(id).map(value -> ModelMapperUtils.map(value, PromotionDto.Response.class)).orElse(null);
    }

    public void save(PromotionDto.Request req) {
        mapper.save(req.toEntity());
    }

    public void update(PromotionDto.Request req) {
        mapper.update(req.toEntity());
    }

    public void updateStatus(PromotionDto.StatusReq req) {
        mapper.updateStatus(req);
    }

    public void delete(Long id) {
        mapper.delete(id);
    }

    public void deletes(PromotionDto.DeleteReq req) {
            mapper.deletes(req);
    }
    public Object getPromotionActive(PromotionDto.ListReq req) {
        return new ResultData<>(ModelMapperUtils.mapAll(mapper.getPromotionActive(req), PromotionDto.ResponseSimple.class));
    }

}
