package com.datn.watch.front.manage.sample.model.entity;

import com.datn.watch.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Article extends AuditableEntity {
    private Long id ;
    private String title;
    private String author;

}

