package com.datn.watch.salespromotion.flashsale.model.dto;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.salespromotion.flashsale.model.entity.FlashSale;
import com.pet.market.api.salespromotion.productflashsale.model.dto.ProductFlashSaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlashSaleDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        String query = "";
        Boolean enabledPage = true;

    }

    @Data
    public static class Request {

        private Long id;
        private String name;
        private String imgBanner;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer status;
        private Integer approveStatus;
        private List<ProductFlashSaleDto.Request> products = new ArrayList<>();


        public FlashSale toEntity() {
            return ModelMapperUtils.map(this, FlashSale.class);
        }
    }

    @Data
    public static class Response {

        private Long id;
        private String name;
        private String imgBanner;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer status;
        private Integer approveStatus;

    }
    @Data
    public static class ResponseDetails {

        private Long id;
        private String name;
        private String imgBanner;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer status;
        private Integer approveStatus;
        private List<ProductFlashSaleDto.Response> products = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    public static class StatusReq {
        private Long id;
        private Integer status;
    }

    @Data
    public static class DeleteReq {
        Long[] ids = new Long[0];
    }
}
