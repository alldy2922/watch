package com.datn.watch.merchandise.review.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Review extends AuditableEntity {
    private Long id;
    private String name;
    private String note;
    private Integer star;
    private Integer likeCnt;
    private String content;
    private Integer typePeople;
    private Integer status = 0;
    private String image;
    private Long idProduct;
    private LocalDateTime dateOfReview;
    private String htmlImageData;
}

