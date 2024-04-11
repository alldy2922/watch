package com.datn.watch.salespromotion.promotion.repository;

import com.pet.market.api.salespromotion.promotion.model.entity.PromotionCust;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface PromotionCustRepository {

    @SelectProvider(type = PromotionCustSqlBuilder.class, method = "save")
    void save(PromotionCust object);

    @SelectProvider(type = PromotionCustSqlBuilder.class, method = "update")
    void update(PromotionCust object);
}
