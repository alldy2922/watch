package com.datn.watch.salespromotion.promotion.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PromotionCust extends AuditableEntity {

    private Long id;
    private Long idPromotion;
    private String idCustomer;
    private Long idOrder;
    private Integer isAddDiscount;

}

