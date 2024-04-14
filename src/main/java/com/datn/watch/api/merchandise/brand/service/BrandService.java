package com.datn.watch.api.merchandise.brand.service;


import com.datn.watch.common.model.json.ResultData;
import com.datn.watch.common.utils.ModelMapperUtils;
import com.datn.watch.common.utils.paging.ResultPageData;
import org.springframework.stereotype.Service;

import com.datn.watch.api.merchandise.brand.model.dto.BrandDto;
import com.datn.watch.api.merchandise.brand.model.entity.Brand;
import com.datn.watch.api.merchandise.brand.repository.BrandRepository;

import java.util.List;

/**
 * @author tobi
 */
@Service
public class BrandService {

    private final BrandRepository mapper;

    public BrandService(BrandRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(BrandDto.BrandListReq req) {
        if (req.getEnabledPage()) {
            int totalRecords = mapper.count(req);
            List<Brand> list = mapper.getAll(req);
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, ModelMapperUtils.mapAll(list, BrandDto.BrandResp.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), BrandDto.BrandResp.class));
        }
    }

    public Object get(Long id) {
        return mapper.getById(id).map(value -> ModelMapperUtils.map(value, BrandDto.BrandResp.class)).orElse(null);
    }

    public void save(BrandDto.BrandReq req) {
        mapper.save(req.toEntity());
    }

    public void update(BrandDto.BrandReq req) {
        mapper.update(req.toEntity());
    }

    public void updateStatus(BrandDto.BrandStatusReq req) {
        mapper.updateStatus(req);
    }

    public void deletes(BrandDto.BrandDeleteReq req) {
        mapper.deletes(req);
    }
}
