package com.datn.watch.salespromotion.promotion.utils;

import com.pet.market.api.common.exception.PromotionException;
import com.pet.market.api.salespromotion.promotion.model.entity.Promotion;
import com.pet.market.api.salespromotion.promotion.model.entity.PromotionArea;
import com.pet.market.api.salespromotion.promotion.model.entity.PromotionCust;
import com.pet.market.api.salespromotion.promotion.model.enums.PromotionType;
import com.pet.market.api.salespromotion.promotion.repository.PromotionAreaRepository;
import com.pet.market.api.salespromotion.promotion.repository.PromotionCustRepository;
import com.pet.market.api.salespromotion.promotion.repository.PromotionRepository;
import org.springframework.stereotype.Component;

@Component
public class PromotionUtils {
    private final PromotionRepository mapper;
    private final PromotionAreaRepository promotionAreaRepository;
    private final PromotionCustRepository promotionCustRepository;

    public PromotionUtils(PromotionRepository mapper, PromotionAreaRepository promotionAreaRepository, PromotionCustRepository promotionCustRepository) {
        this.mapper = mapper;
        this.promotionAreaRepository = promotionAreaRepository;
        this.promotionCustRepository = promotionCustRepository;
    }

    public Promotion getById(Long id) {
        return mapper.getById(id)
                .orElseThrow(() -> new PromotionException(PromotionException.PROMOTION_NOT_FOUND));
    }

    public void promotionIsUse(Long promotionId, String customerId, Long orderId) {
        Promotion promotion = getById(promotionId);
        if (PromotionType.CUSTOMER.toString().equals(promotion.getType())){
            PromotionCust promotionDetails = new PromotionCust();
            promotionDetails.setIdPromotion(promotionId);
            promotionDetails.setIdCustomer(customerId);
            promotionDetails.setIdOrder(orderId);
            promotionDetails.setIsAddDiscount(0);
            promotionCustRepository.save(promotionDetails);
        }else{
            PromotionArea promotionDetails = new PromotionArea();
            promotionDetails.setIdPromotion(promotionId);
            promotionDetails.setIdOrder(orderId);
            promotionDetails.setIdArea(orderId);
            promotionDetails.setIsAddDiscount(0);
            promotionAreaRepository.save(promotionDetails);
        }
    }
}
