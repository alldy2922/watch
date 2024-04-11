package com.datn.watch.request.model.dto;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public class RequestDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListReq extends Search {
        Boolean enabledPage = true;
        String query = "" ;
    }
    @Data
    public static class ListIdReq{
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
        private String title;
        private String summary;

        @NotNull
        private Long typeId;
        @NotNull
        private String type;
        @NotNull
        private String typeName;

        private String method;
        private LocalDateTime deadline;
        private String comment;
        private Integer status = 0;
        private Boolean isDeleted = false;
        public com.pet.market.api.request.model.entity.Request toEntity() {
            return ModelMapperUtils.map(this, com.pet.market.api.request.model.entity.Request.class);
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String title;
        private String summary;
        private Long typeId;
        private String type;
        private String typeName;
        private String method;
        private LocalDateTime deadline;
        private String comment;
        private Integer status;
        private Boolean isDeleted = false;
        private String createdBy;
    }
}
