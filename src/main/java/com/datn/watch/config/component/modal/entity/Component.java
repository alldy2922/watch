package com.datn.watch.config.component.modal.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Component extends AuditableEntity {
    private Long id;
    private String name;
    private String data;
    private String templateData;
    private String templateName;
    private String imgPreview;
    private String htmlAdmin;
    private String htmlUser;
    private Long idTemplate;
    private int status;
}
