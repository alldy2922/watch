package com.datn.watch.salespromotion.promotion.repository;

import com.pet.market.api.salespromotion.promotion.model.dto.PromotionDto;
import com.pet.market.api.salespromotion.promotion.model.entity.Promotion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PromotionRepository {

    @SelectProvider(type= PromotionSqlBuilder.class, method="getAll")
    List<Promotion> getAll(PromotionDto.ListReq req);

    @SelectProvider(type= PromotionSqlBuilder.class, method="findAll")
    List<Promotion> findAll(PromotionDto.ListReq req);

    @SelectProvider(type= PromotionSqlBuilder.class, method="getById")
    Optional<Promotion> getById(Long id);
    @SelectProvider(type= PromotionSqlBuilder.class, method="save")
    void save(Promotion object);

    @SelectProvider(type= PromotionSqlBuilder.class, method="delete")
    void delete(Long id);
    @SelectProvider(type= PromotionSqlBuilder.class, method="deletes")
    void deletes(PromotionDto.DeleteReq req);
    @SelectProvider(type= PromotionSqlBuilder.class, method="updateStatus")
    void updateStatus(PromotionDto. StatusReq req);

    @SelectProvider(type= PromotionSqlBuilder.class, method="count")
    int count(PromotionDto. ListReq req);
    @SelectProvider(type = PromotionSqlBuilder.class, method = "update")
    void update(Promotion object);
    @SelectProvider(type= PromotionSqlBuilder.class, method="getPromotionActive")
    List<Promotion> getPromotionActive(PromotionDto.ListReq req);

}
