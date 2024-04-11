package com.datn.watch.request.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import com.pet.market.api.common.utils.AuthUtils;
import com.pet.market.api.notification.enumeration.NotificationType;
import com.pet.market.api.notification.model.entity.Notification;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Request extends AuditableEntity {
    private Long id;
    private String title;
    private String summary;
    private String type;
    private Long typeId;
    private String typeName;
    private String method;
    private LocalDateTime deadline;
    private String comment;
    private Integer status = 0;
    private Boolean isDeleted = false;

    public Notification toNotification(String username) {
        Notification notification = new Notification();
        notification.setReceiver(username);
        notification.setSender(AuthUtils.getUserId());
        notification.setContent(this.getSummary());
        notification.setType(NotificationType.REQUEST.name());
        notification.setTargetId(this.getId());
        notification.setUrl("/admin/request");
        if (status == 1){
            notification.setSubject("[APPROVAL]" +"[" + this.getMethod() + "] " + this.getTitle());
        } else if (status == 2) {
            notification.setSubject("[REJECTED]" +"[" + this.getMethod() + "] " + this.getTitle());
        }else {
            notification.setSubject("[" + this.getMethod() + "] " + this.getTitle());
        }
        return notification;
    }
}

