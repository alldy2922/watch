package com.datn.watch.user.account.modal.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditableEntity {
    private Long id;
    private String name;
    private String phoneNumber;
    private String username;
    private String password;
    private Integer status;
}
