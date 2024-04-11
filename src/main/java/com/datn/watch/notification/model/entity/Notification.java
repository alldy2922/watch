package com.datn.watch.notification.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.notification.model.dto.NotificationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends AuditableEntity {
    private Long id;
    private String sender;
    private String receiver;
    private String type;
    private String subject;
    private String content;
    private String url;
    private Boolean isRead = false;
    private Boolean isDeleted = false;
    private Long targetId;
    public Notification(Notification other) {
        this.id = other.id;
        this.sender = other.sender;
        this.receiver = other.receiver;
        this.type = other.type;
        this.url = other.url;
        this.subject = other.subject;
        this.content = other.content;
        this.isRead = other.isRead;
        this.isDeleted = other.isDeleted;
        this.targetId = other.targetId;
    }
    public Map<String, Object> convertToObjectOfFRDB(){
        Map<String, Object> updateFields = new HashMap<>();
        updateFields.put(String.valueOf(this.getId()), ModelMapperUtils.map(this, NotificationDto.Response.class));
        return updateFields;
    }
}
