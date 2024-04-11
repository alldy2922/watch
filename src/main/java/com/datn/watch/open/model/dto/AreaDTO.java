package com.datn.watch.open.model.dto;

import com.pet.market.api.open.model.Area;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public class AreaDTO {

    @Data
    public static class AreaRes {

        private String id;

        private String value;

        public static AreaRes toRes(Area source) {
            AreaRes res = new AreaRes();
            BeanUtils.copyProperties(source, res);
            return res;
        }

    }

}
