package com.datn.watch.request.utils;

import com.pet.market.api.notification.utils.NotificationUtils;
import com.pet.market.api.request.enumeration.RequestType;
import com.pet.market.api.request.model.entity.Request;
import com.pet.market.api.request.repository.RequestRepository;
import com.pet.market.api.salespromotion.flashsale.model.dto.FlashSaleDto;
import com.pet.market.api.salespromotion.flashsale.utils.FlashSaleUtils;
import org.springframework.stereotype.Component;

/**
 * @author mttai
 * @date 5/1/2024
 * @desc
 */
@Component
public class RequestUtils {
    private static RequestRepository requestMapper;



    public RequestUtils(RequestRepository requestMapper) {
        RequestUtils.requestMapper = requestMapper;
    }

    public static void approveStatus(Request request) {
        switch (RequestType.valueOf(request.getType())) {
            case SALE_PROMOTION_FLASH_SALE ->
                    FlashSaleUtils.approveStatus(new FlashSaleDto.StatusReq(request.getTypeId(), request.getStatus()));
            case PARTNER_SHIPPING_UNIT -> {

            }
        }
    }

    public static void createRequest(Request request) {
        requestMapper.save(request);
        NotificationUtils.createNotification(request);
    }
}
