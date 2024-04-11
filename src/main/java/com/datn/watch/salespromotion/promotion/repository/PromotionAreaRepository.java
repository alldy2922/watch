package com.datn.watch.salespromotion.promotion.repository;

import com.pet.market.api.salespromotion.promotion.model.entity.PromotionArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface PromotionAreaRepository {

    @SelectProvider(type = PromotionAreaSqlBuilder.class, method = "save")
    void save(PromotionArea object);

    @SelectProvider(type = PromotionAreaSqlBuilder.class, method = "update")
    void update(PromotionArea object);
}
