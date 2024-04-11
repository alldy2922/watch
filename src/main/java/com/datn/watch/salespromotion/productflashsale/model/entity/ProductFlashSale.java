package com.datn.watch.salespromotion.productflashsale.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductFlashSale extends AuditableEntity {
    private Long id;
    private Long idFlashSale;
    private Long idProduct;
    private Long discountPrice;
    private Integer sellQuantity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer addDiscount;
    private Integer status;
    private String productName;
}

