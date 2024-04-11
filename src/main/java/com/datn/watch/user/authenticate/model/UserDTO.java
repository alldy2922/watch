package com.datn.watch.user.authenticate.model;

import com.pet.market.api.user.authenticate.model.entity.UserAuth;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

public class UserDTO {

    @Getter
    @Setter
    public static class UserRes {

        private String userId;

        private Integer forceChange;

        private String userName;

        private String phoneNumber;

        private String address;

        private Integer isActive;

        private String role;

        public UserRes toUserRes(UserAuth auth) {
            BeanUtils.copyProperties(auth, this);
            return this;
        }

    }

}
