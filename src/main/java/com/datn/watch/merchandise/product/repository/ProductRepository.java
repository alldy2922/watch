package com.datn.watch.merchandise.product.repository;

import com.pet.market.api.merchandise.product.model.dto.ProductDto;
import com.pet.market.api.merchandise.product.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductRepository {

    @SelectProvider(type = ProductSqlBuilder.class, method = "getAll")
    List<Product> getAll(ProductDto.ListReq req);

    @SelectProvider(type = ProductSqlBuilder.class, method = "findAll")
    List<Product> findAll(ProductDto.ListReq req);

    @SelectProvider(type = ProductSqlBuilder.class, method = "getById")
    Optional<Product> getById(Long id);

    @SelectProvider(type = ProductSqlBuilder.class, method = "save")
    void save(Product brand);

    @SelectProvider(type = ProductSqlBuilder.class, method = "delete")
    void delete(Long id);
    @SelectProvider(type = ProductSqlBuilder.class, method = "deletes")
    void deletes(ProductDto.DeleteReq req);

    @SelectProvider(type = ProductSqlBuilder.class, method = "update")
    void update(Product brand);

    @SelectProvider(type = ProductSqlBuilder.class, method = "count")
    int count(ProductDto.ListReq req);

    @SelectProvider(type = ProductSqlBuilder.class, method = "updateStatus")
    void updateStatus(ProductDto.StatusReq req);

    @Select({"""
            <script>
                SELECT A.ID, NAME, A.PRICE, A.THUMBNAIL_PC,
                A.THUMBNAIL_MO, A.IMAGE, A.UNIT, A.WEIGHT, A.INFO, A.PREFERENTIAL, A.STATUS, A.ID_CATEGORY, A.ID_BRAND, A.AMOUNT, A.`ATTRIBUTES`,
                CASE WHEN B.PRICE IS NULL OR B.PRICE = 0 THEN A.PRICE
                ELSE B.PRICE - IFNULL(B.ADD_DISCOUNT, 0) END AS price
                FROM TB_PRODUCT A LEFT JOIN (
                    SELECT ID_PRODUCT, PRICE, ADD_DISCOUNT
                    FROM (
                             SELECT ID_PRODUCT, PRICE, ADD_DISCOUNT, dense_rank() over ( partition by ID_PRODUCT order by CREATED_DATE desc ) as CNT
                             FROM TB_SELL_PLAN
                             WHERE NOW() BETWEEN START_DATE AND END_DATE
                         ) SALE_PLAN
                    WHERE CNT = 1
                ) B ON A.ID = B.ID_PRODUCT
                WHERE ID IN
                <foreach collection='ids' index= 'index' item= 'element' open='(' separator=',' close=')' >
            			#{element}
            		</foreach>
            </script>
            """})
    List<Product> getProductByIds(List<Long> ids);

}
