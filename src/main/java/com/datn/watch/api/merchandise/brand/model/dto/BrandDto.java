package com.datn.watch.api.merchandise.brand.model.dto;

import com.datn.watch.api.merchandise.brand.model.entity.Brand;

import com.datn.watch.common.utils.ModelMapperUtils;
import com.datn.watch.common.utils.paging.Search;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class BrandDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class BrandListReq extends Search {
        String query = "";
        Boolean enabledPage = true;

    }

    @Data
    public static class BrandReq {

        private Long id;
        private String name;
        private Integer sort;
        private String image;
        private Integer status;

        public Brand toEntity() {
            return ModelMapperUtils.map(this, Brand.class);
        }
    }

    @Data
    public static class BrandResp {

        private Long id;
        private String name;
        private Integer sort;
        private String image;
        private Integer status;
    }

    @Data
    public static class BrandStatusReq {
        private Long id;
        private Integer status;
    }

    @Data
    public static class BrandDeleteReq {
        Long[] ids = new Long[0];
    }
}
