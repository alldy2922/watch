package com.datn.watch.merchandise.category.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.merchandise.category.model.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


public class CategoryDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        Boolean enabledPage = true;
        String query = "" ;
        Integer level;
    }

    @Data
    public static class CategoryDeleteReq{
        Long[] ids = new Long[0];
    }
    @Data
    public static class StatusReq{
        private Long id;
        private Integer status;
    }
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CategoryReq {
        private Long id;
        private Integer level;
        private Integer parent;
        private String name;
        private String linkPage;
        private String iconUrl;
        private String url;
        private String attributes = "{}";
        private Integer hasTop;
        private Integer sort;
        @NotBlank
        private String banner;
        @NotBlank
        private String bannerPc;
        private Integer status;
        private String htmlData;
        public Category toEntity() {
            return ModelMapperUtils.map(this, Category.class);
        }
    }

    @Data
    public static class CategoryResp {

        private Long id;
        private String level;
        private String name;
        private String parent;
        private String linkPage;
        private String url;
        private String attributes;
        private Integer hasTop;
        private String banner;
        private String bannerPc;
        private Integer status;
        private String htmlData;
        private String iconUrl;
        private Integer sort;
    }
    @Data
    public static class CategoryAttrResp {
        private Long id;
        private String attributes;
    }

    @Data
    @AllArgsConstructor
    public static class IdsReq{
        Long[] ids;
    }
    @Data
    public static class DeleteReq{
        Long[] ids = new Long[0];
    }
}
