package com.datn.watch.merchandise.brand.model.dto;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.merchandise.brand.model.entity.Brand;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class BrandDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class BrandListReq extends Search {
        String query = "" ;
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
    public static class BrandStatusReq{
        private Long id;
        private Integer status;
    }
    @Data
    public static class BrandDeleteReq {
        Long[] ids = new Long[0];
    }
}
