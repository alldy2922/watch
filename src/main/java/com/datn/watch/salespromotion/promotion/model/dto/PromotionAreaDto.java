package com.datn.watch.salespromotion.promotion.model.dto;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.salespromotion.promotion.model.entity.PromotionArea;
import lombok.Data;
import lombok.EqualsAndHashCode;


public class PromotionAreaDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        String query = "";
        Boolean enabledPage = true;
        private Long idPromotion;
        private Long idArea;
        private Integer isAddDiscount;
    }

    @Data
    public static class Request {

        private Long id;
        private Long idPromotion;
        private Long idArea;
        private Integer isAddDiscount;

        public PromotionArea toEntity() {
            return ModelMapperUtils.map(this, PromotionArea.class);
        }
    }

    @Data
    public static class Response {

        private Long id;
        private Long idPromotion;
        private Long idArea;
        private Integer isAddDiscount;
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
