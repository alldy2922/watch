package com.datn.watch.notification.model.dto;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.notification.model.entity.Notification;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

public class NotificationDto {

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
        private Instant time;
        private String sender;
        private String receiver;
        private String type;
        private String subject;
        private String content;
        private Boolean isRead;
        private Boolean isDeleted;
        private Long targetId;
        public Notification toEntity() {
            return ModelMapperUtils.map(this, Notification.class);
        }
    }

    @Data
    public static class Response {
        private Long id;
        private Instant time;
        private String sender;
        private String receiver;
        private String type;
        private String subject;
        private String content;
        private Boolean isRead;
        private Long targetId;
        private String url;
    }
}
