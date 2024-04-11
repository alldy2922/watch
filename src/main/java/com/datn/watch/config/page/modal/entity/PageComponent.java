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
public class PageComponent extends AuditableEntity {
    private Long id;
    private Long idComponent;
    private Long idPage;
}
