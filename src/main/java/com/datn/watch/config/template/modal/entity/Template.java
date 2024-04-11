package com.datn.watch.config.template.modal.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template extends AuditableEntity {
    private long id;
    private String templateName;
    private String type;
    private String htmlAdmin;
    private String htmlUser;
    private String imgPreview;
    private int status;
}
