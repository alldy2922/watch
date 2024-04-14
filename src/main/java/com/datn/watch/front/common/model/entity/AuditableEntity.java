package com.datn.watch.front.common.model.entity;//package com.pet.market.api.common.model.entity;
//
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.MappedSuperclass;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//
///**
// * @author tobi
// * @Date : 10/4/2023
// * @Time : 12:47 AM
// * @desc : ...
// */
//@Getter
//@Setter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public class AuditableEntity<U>  {
//    @CreatedBy
//    @Column(name = "created_by")
//    private U createdBy = null;
//
//
//    @CreatedDate
//    @Column(name = "created_date")
//    private LocalDateTime createdDate = null;
//
//
//    @LastModifiedBy
//    @Column(name = "last_modified_by")
//    private U lastModifiedBy = null;
//
//
//    @LastModifiedDate
//    @Column(name = "last_modified_date")
//    private LocalDateTime lastModifiedDate = null;
//
//}
