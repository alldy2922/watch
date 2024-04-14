package com.datn.watch.api.merchandise.review.model.dto;

import com.datn.watch.common.utils.ModelMapperUtils;
import com.datn.watch.common.utils.paging.Search;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

import com.datn.watch.api.merchandise.review.model.entity.Review;

public class ReviewDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        Boolean enabledPage = true;
        String query = "" ;
    }
    @Data
    public static class DeleteReq{
        Long[] ids = new Long[0];
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
        private String note;
        private String star;
        private String likeCnt;
        private String content;
        private LocalDateTime dateOfReview;
        private String typePeople;
        private String status;
        private String image;
        private String htmlImageData;
        private Long idProduct;
        public Review toEntity() {
            return ModelMapperUtils.map(this, Review.class);
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private String note;
        private Integer star;
        private Integer likeCnt;
        private String content;
        private Integer typePeople;
        private Integer status;
        private String image;
        private Integer idProduct;
        private String createdDate;
        private LocalDateTime dateOfReview;
        private String htmlImageData;
    }
}
