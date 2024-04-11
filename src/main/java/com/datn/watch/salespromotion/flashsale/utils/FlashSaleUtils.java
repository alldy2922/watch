package com.datn.watch.salespromotion.flashsale.utils;

import com.pet.market.api.salespromotion.flashsale.model.dto.FlashSaleDto;
import com.pet.market.api.salespromotion.flashsale.repository.FlashSaleRepository;
import org.springframework.stereotype.Component;

@Component
public class FlashSaleUtils {
    private static FlashSaleRepository mapper;

    public FlashSaleUtils(FlashSaleRepository mapper) {
        FlashSaleUtils.mapper = mapper;
    }
    public static void approveStatus(FlashSaleDto.StatusReq req){
        mapper.approveStatus(req);
    }
}
