package com.datn.watch.salespromotion.promotion.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Promotion extends AuditableEntity {

    private Long id;
    private String name;
    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer unLimitTime;
    private Integer status;
    private Integer approveStatus;
    private Long value;

}

