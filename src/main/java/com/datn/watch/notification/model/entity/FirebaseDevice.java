package com.datn.watch.notification.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;

@Data
public class FirebaseDevice extends AuditableEntity {
    private Long id;
    private String firebaseToken;
    private String os;
    private String username;
    private String deviceToken;
    private Boolean isEnabled = true;
    private Boolean isDeleted = false;
}
