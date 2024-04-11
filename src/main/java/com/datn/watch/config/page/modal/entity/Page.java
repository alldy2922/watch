package com.datn.watch.config.page.modal.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page extends AuditableEntity {
    private long id;
    private String name;
    private String type;
    private String data;
    private String htmlData;
    private int status;
}
