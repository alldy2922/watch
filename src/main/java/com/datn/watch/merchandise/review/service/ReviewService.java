package com.datn.watch.merchandise.review.service;

import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.merchandise.review.model.dto.ReviewDto;
import com.pet.market.api.merchandise.review.model.entity.Review;
import com.pet.market.api.merchandise.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tobi
 */
@Service
public class ReviewService {

    private final ReviewRepository mapper;

    public ReviewService(ReviewRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(ReviewDto.ListReq req) {
        if (req.getEnabledPage()) {
            int totalRecords = mapper.count();
            List<Review> list = mapper.getAll(req);
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, ModelMapperUtils.mapAll(list, ReviewDto.Response.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), ReviewDto.Response.class));
        }
    }

    public Object get(Long id) {
        return mapper.getCategoryById(id).map(value -> ModelMapperUtils.map(value, ReviewDto.Response.class)).orElse(null);
    }

    public void save(ReviewDto.Request req) {
        mapper.save(req.toEntity());
    }

    public void update(ReviewDto.Request req) {
        mapper.update(req.toEntity());
    }

    public void delete(Long id) {
        mapper.delete(id);
    }

    public void updateStatus(ReviewDto.StatusReq req) {
        mapper.updateStatus(req);
    }

    public void deletes(ReviewDto.DeleteReq req) {
        mapper.deletes(req);
    }
}
