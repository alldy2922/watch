package com.datn.watch.merchandise.category.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends AuditableEntity {

    private Long id;
    private String name;
    private Integer level;
    private Long parent;
    private String linkPage;
    private String url;
    private String attributes;
    private Integer hasTop;
    private Integer sort;
    private String banner;
    private String bannerPc;
    private Integer status;
    private String htmlData;
    private String iconUrl;

}

