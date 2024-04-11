//package com.datn.watch.common.model.entity;
//
//import com.pet.market.api.notification.enumeration.NotificationType;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Data
//public class PushMessage {
//    @NotBlank(message = "username should not be null")
//    @Size(max = 255, message = "username's length should be less than 255 characters")
//    private String username;
//
//    @NotNull(message = "Subject not null")
//    private String subject;
//
//    @NotNull(message = "Content not null")
//    private String content;
//
//    private NotificationType type;
//
//    private Long targetId;
//
//    private LocalDateTime createdDate = LocalDateTime.now();
//}
