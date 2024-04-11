package com.datn.watch.salespromotion.promotion.model.dto;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.salespromotion.promotion.model.entity.Promotion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public class PromotionDto {

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
        private String type;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer unLimitTime;
        private Integer status;
        private Integer approveStatus;

        public Promotion toEntity() {
            return ModelMapperUtils.map(this, Promotion.class);
        }
    }

    @Data
    public static class Response {

        private Long id;
        private String name;
        private String type;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer unLimitTime;
        private Integer status;
        private Integer approveStatus;
    }
    @Data
    public static class ResponseSimple {
        private Long id;
        private String name;
        private String type;
        private Integer value;
    }

    @Data
    public static class StatusReq {
        private Long id;
        private Integer status;
    }

    @Data
    public static class DeleteReq {
        Long[] ids = new Long[0];
    }
}
