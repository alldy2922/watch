package com.datn.watch.salespromotion.productflashsale.repository;

import com.pet.market.api.salespromotion.productflashsale.model.dto.ProductFlashSaleDto;
import com.pet.market.api.salespromotion.productflashsale.model.entity.ProductFlashSale;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductFlashSaleRepository {
    @SelectProvider(type= ProductFlashSaleSqlBuilder.class, method="getAll")
    List<ProductFlashSale> getAll(ProductFlashSaleDto.ListReq req);
    @SelectProvider(type= ProductFlashSaleSqlBuilder.class, method="findAll")
    List<ProductFlashSale> findAll(ProductFlashSaleDto.ListReq req);
    @SelectProvider(type= ProductFlashSaleSqlBuilder.class, method="getById")
    Optional<ProductFlashSale> getById(Long id);
    @InsertProvider(type= ProductFlashSaleSqlBuilder.class, method="save")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(ProductFlashSale req);
    @SelectProvider(type= ProductFlashSaleSqlBuilder.class, method="delete")
    void delete(Long id);
    @SelectProvider(type= ProductFlashSaleSqlBuilder.class, method="deletes")
    void deletes(ProductFlashSaleDto.DeleteReq ids);
    @SelectProvider(type= ProductFlashSaleSqlBuilder.class, method="updateStatus")
    void updateStatus(ProductFlashSaleDto. StatusReq req);
    @SelectProvider(type= ProductFlashSaleSqlBuilder.class, method="count")
    int count(ProductFlashSaleDto. ListReq req);
    @UpdateProvider(type = ProductFlashSaleSqlBuilder.class, method = "update")
    void update(ProductFlashSale req);
}
