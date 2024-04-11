package com.datn.watch.user.role.modal.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AuditableEntity {
    private Long id;
    private String code;
    private String description;
    private Integer status;
}
