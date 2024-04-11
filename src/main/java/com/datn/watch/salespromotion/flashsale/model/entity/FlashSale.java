package com.datn.watch.salespromotion.flashsale.model.entity;

import com.pet.market.api.common.model.entity.AuditableEntity;
import com.pet.market.api.common.utils.DateTimeUtils;
import com.pet.market.api.request.enumeration.RequestType;
import com.pet.market.api.request.model.entity.Request;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author tobi
 */
@Data
public class FlashSale extends AuditableEntity {
    private Long id;
    private String name;
    private String imgBanner;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer status;
    private Integer approveStatus = 0;
    private Boolean isDeleted ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FlashSale flashSale = (FlashSale) o;
        return Objects.equals(id, flashSale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public Request toRequest() {
        Request request = new Request();
        request.setMethod("POST");
        request.setType(RequestType.SALE_PROMOTION_FLASH_SALE.getKey());
        request.setTypeName(RequestType.SALE_PROMOTION_FLASH_SALE.getName());
        request.setTitle("[FlashSale] " + this.getName());
        request.setSummary("from : " + DateTimeUtils.formatDateTime(this.getStartDate()) + " to : " + DateTimeUtils.formatDateTime(this.getEndDate()));
        request.setTypeId(this.getId());
        return request;
    }
}

