package com.datn.watch.merchandise.brand.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Brand extends AuditableEntity {

    private Long id;
    private String name;
    private Long sort;
    private String image;
    private Long status;

}

