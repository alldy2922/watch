package com.datn.watch.common.model.entity;

import com.datn.watch.common.utils.AuthUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditableEntity {

    private String createdBy = AuthUtils.getUserId();

    private LocalDateTime createdDate = LocalDateTime.now();

    private String lastModifiedBy = AuthUtils.getUserId();

    private LocalDateTime lastModifiedDate = LocalDateTime.now();

}
