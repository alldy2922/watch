package com.datn.watch.api.merchandise.brand.model.entity;

import com.datn.watch.common.model.entity.AuditableEntity;

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

