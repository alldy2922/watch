package com.datn.watch.salespromotion.flashsale.repository;

import com.pet.market.api.salespromotion.flashsale.model.dto.FlashSaleDto;
import com.pet.market.api.salespromotion.flashsale.model.entity.FlashSale;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FlashSaleRepository {
    @SelectProvider(type= FlashSaleSqlBuilder.class, method="getAll")
    List<FlashSale> getAll(FlashSaleDto.ListReq req);
    @SelectProvider(type= FlashSaleSqlBuilder.class, method="findAll")
    List<FlashSale> findAll(FlashSaleDto.ListReq req);
    @SelectProvider(type= FlashSaleSqlBuilder.class, method="getById")
    Optional<FlashSale> getById(Long id);
    @InsertProvider(type= FlashSaleSqlBuilder.class, method="save")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(FlashSale flashSale);
    @SelectProvider(type= FlashSaleSqlBuilder.class, method="delete")
    void delete(Long id);
    @SelectProvider(type= FlashSaleSqlBuilder.class, method="deletes")
    void deletes(FlashSaleDto.DeleteReq ids);
    @SelectProvider(type= FlashSaleSqlBuilder.class, method="updateStatus")
    void updateStatus(FlashSaleDto. StatusReq req);
    @SelectProvider(type= FlashSaleSqlBuilder.class, method="count")
    int count(FlashSaleDto. ListReq req);
    @UpdateProvider(type = FlashSaleSqlBuilder.class, method = "update")
    void update(FlashSale object);
    @SelectProvider(type = FlashSaleSqlBuilder.class, method = "findAllWithinDate")
    List<FlashSale> findAllWithinDate(FlashSale flashSale);
    @SelectProvider(type = FlashSaleSqlBuilder.class, method = "approveStatus")
    void approveStatus(FlashSaleDto.StatusReq req);
}
