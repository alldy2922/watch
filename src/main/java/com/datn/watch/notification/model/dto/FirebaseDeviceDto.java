package com.datn.watch.notification.model.dto;

import com.pet.market.api.common.enums.OS;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.Search;
import com.pet.market.api.notification.model.entity.FirebaseDevice;
import lombok.Data;
import lombok.EqualsAndHashCode;


public class FirebaseDeviceDto {

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
        private String firebaseToken;
        private OS os;
        public FirebaseDevice toEntity() {
            return ModelMapperUtils.map(this, FirebaseDevice.class);
        }
    }

    @Data
    public static class Response {
        private String firebaseToken;
        private OS os;
    }
}
