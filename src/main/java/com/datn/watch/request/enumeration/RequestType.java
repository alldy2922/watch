package com.datn.watch.request.enumeration;

import lombok.Getter;

/**
 * @author mttai
 * @date 5/1/2024
 * @desc
 */
@Getter
public enum RequestType {
    SALE_PROMOTION_FLASH_SALE("SALE_PROMOTION_FLASH_SALE", "Ưu đãi - Khuyễn mãi"),
    PARTNER_SHIPPING_UNIT("PARTNER_SHIPPING_UNIT", "Đối tác - Đơn vị vận chuyển")
    ;
    private final String key;
    private final String name;

    RequestType(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
