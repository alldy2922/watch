package com.datn.watch.merchandise.product.model.dto;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.merchandise.product.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class ProductDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        Boolean enabledPage = true;
        String query = "" ;
        Long[] categoryIds = new Long[0];
        Long[] brandIds = new Long[0];
        Long[] isStops = new Long[0];
        String sortPrice = "";
    }
    @Data
    public static class DeleteReq{
        Long[] ids = new Long[0];
    }
    @Data
    @AllArgsConstructor
    public static class IdsReq{
        Long[] ids ;
    }

    @Data
    public static class StatusReq{
        private Long id;
        private Integer status;
    }
    @Data
    public static class Request {
        private Long id;
        private String name;
        private Integer price;
        private String thumbnailPc = Constant.IMG_LINK;
        private String thumbnailMo = Constant.IMG_LINK;
        private String image;
        private String unit;
        private String weight;
        private String info;
        private String preferential;
        private Integer status;
        private String idCategory;
        private String idBrand;
        private Integer amount;
        private String attributes;
        private String htmlImageData;
        private String htmlAttributesData;
        private String shoppeLink;
        private String tiktokLink;
        private String qrCode = Constant.IMG_LINK;
        private String sort;
        private String isNew;
        private String isBestSale;
        private String isStop;
        public Product toEntity() {
            return ModelMapperUtils.map(this, Product.class);
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private String price;
        private String thumbnail;
        private String image;
        private String unit;
        private String weight;
        private String info;
        private String preferential;
        private Integer status;
        private String idCategory;
        private String idBrand;
        private String amount;
        private String attributes;
        private String thumbnailPc;
        private String thumbnailMo;
        private String isStop;

    }
    @Data
    public static class DetailResponse {
        private Long id;
        private String name;
        private String price;
        private String thumbnail;
        private String image;
        private String unit;
        private String weight;
        private String info;
        private String preferential;
        private Integer status;
        private String idCategory;
        private String idBrand;
        private String amount;
        private String attributes;
        private String htmlImageData;
        private String htmlAttributesData;
        private String shoppeLink;
        private String tiktokLink;
        private String qrCode;
        private Integer sort;
        private String isNew;
        private String isBestSale;
        private String isStop;
        private String thumbnailPc;
        private String thumbnailMo;
    }
}
