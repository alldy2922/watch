package com.datn.watch.salespromotion.productflashsale.model.dto;

import com.pet.market.api.common.model.entity.AuditableEntity;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.salespromotion.productflashsale.model.entity.ProductFlashSale;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public class ProductFlashSaleDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        String query = "";
        Boolean enabledPage = true;
        Long idFlashSale ;
        Boolean hasProductName = false;
    }

    @Data
    public static class Request {
        private Long id;
        private Long idFlashSale;
        private Long idProduct;
        private Long discountPrice;
        private Integer sellQuantity;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer addDiscount;
        private Integer status;
        public ProductFlashSale toEntity() {
            return ModelMapperUtils.map(this, ProductFlashSale.class);
        }
    }

    @Data
    public static class Response {
        private Long id;
        private Long idFlashSale;
        private Long idProduct;
        private Long discountPrice;
        private Integer sellQuantity;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer addDiscount;
        private Integer status;
        private String productName;

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class StatusReq extends AuditableEntity {
        private Long id;
        private Integer status;
    }

    @Data
    public static class DeleteReq {
        Long[] ids = new Long[0];
    }
}
