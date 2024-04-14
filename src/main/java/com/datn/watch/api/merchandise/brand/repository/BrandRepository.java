package com.datn.watch.api.merchandise.brand.repository;

import org.apache.ibatis.annotations.*;

import com.datn.watch.api.merchandise.brand.model.dto.BrandDto;
import com.datn.watch.api.merchandise.brand.model.entity.Brand;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BrandRepository {

    @SelectProvider(type= BrandSqlBuilder.class, method="getAll")
    List<Brand> getAll(BrandDto.BrandListReq req);

    @SelectProvider(type= BrandSqlBuilder.class, method="findAll")
    List<Brand> findAll(BrandDto.BrandListReq req);

    @SelectProvider(type= BrandSqlBuilder.class, method="getById")
    Optional<Brand> getById(Long id);
    @SelectProvider(type=BrandSqlBuilder.class, method="save")
    void save(Brand brand);

    @SelectProvider(type=BrandSqlBuilder.class, method="delete")
    void delete(Long id);
    @SelectProvider(type=BrandSqlBuilder.class, method="updateStatus")
    void updateStatus(BrandDto.BrandStatusReq req);
    @Update("""
            UPDATE
            \tMARKET.TB_BRAND
            SET
            \tUPDATED_BY = #{lastModifiedBy},
            \tUPDATED_DATE = now(),
            \tNAME = #{name},
            \tSORT = #{sort},
            \tIMAGE = #{image},
            \tSTATUS = #{status}
            WHERE
            \tID = #{id}\s""")
    void update(Brand brand);

    @SelectProvider(type=BrandSqlBuilder.class, method="count")
    int count(BrandDto.BrandListReq req);
    @SelectProvider(type= BrandSqlBuilder.class, method="deletes")
    void deletes(BrandDto.BrandDeleteReq ids);
}
