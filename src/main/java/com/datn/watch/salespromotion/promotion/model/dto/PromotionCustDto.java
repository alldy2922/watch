package com.datn.watch.salespromotion.promotion.model.dto;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.salespromotion.promotion.model.entity.PromotionCust;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class PromotionCustDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        String query = "";
        Boolean enabledPage = true;
        private Long idPromotion;
        private Long idCustomer;
        private Integer isAddDiscount;
    }

    @Data
    public static class Request {

        private Long id;
        private Long idPromotion;
        private Long idCustomer;
        private Integer isAddDiscount;

        public PromotionCust toEntity() {
            return ModelMapperUtils.map(this, PromotionCust.class);
        }
    }

    @Data
    public static class Response {

        private Long id;
        private Long idPromotion;
        private Long idCustomer;
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
