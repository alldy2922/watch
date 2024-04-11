package com.datn.watch.salespromotion.productflashsale.repository;

import com.pet.market.api.salespromotion.productflashsale.model.dto.ProductFlashSaleDto;
import com.pet.market.api.salespromotion.productflashsale.model.entity.ProductFlashSale;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;

public class ProductFlashSaleSqlBuilder {
    private static final String TABLE_NAME = "TB_PRODUCT_FLASH_SALE";

    public String getAll(ProductFlashSaleDto.ListReq req) {
        return new SQL() {{
            if(req.getHasProductName()){
                SELECT("a.ID, a.ID_FLASH_SALE, a.ID_PRODUCT, b.NAME as productName, a.DISCOUNT_PRICE, a.SELL_QUANTITY, a.START_DATE, a.END_DATE, a.ADD_DISCOUNT, a.STATUS");
                FROM(TABLE_NAME + " a");
                LEFT_OUTER_JOIN("TB_PRODUCT b ON a.ID_PRODUCT = b.ID");
            }else{
                SELECT("a.ID, a.ID_FLASH_SALE, a.ID_PRODUCT, a.DISCOUNT_PRICE, a.SELL_QUANTITY, a.START_DATE, a.END_DATE, a.ADD_DISCOUNT, a.STATUS");
                FROM(TABLE_NAME + " a");
            }
            WHERE("a.IS_DELETED = " + 0);
            if (req.getIdFlashSale() != null && req.getIdFlashSale() != 0) {
                AND().WHERE("ID_FLASH_SALE = #{idFlashSale}");
            }
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String getById(Long id) {
        return new SQL() {{
            SELECT("ID, ID_FLASH_SALE, ID_PRODUCT, DISCOUNT_PRICE, SELL_QUANTITY, START_DATE, END_DATE, ADD_DISCOUNT, STATUS");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String findAll(ProductFlashSaleDto.ListReq req) {
        return new SQL() {{
            if(req.getHasProductName()){
                SELECT("a.ID, a.ID_FLASH_SALE, a.ID_PRODUCT, b.NAME as productName, a.DISCOUNT_PRICE, a.SELL_QUANTITY, a.START_DATE, a.END_DATE, a.ADD_DISCOUNT, a.STATUS");
                FROM(TABLE_NAME + " a");
                LEFT_OUTER_JOIN("TB_PRODUCT b ON a.ID_PRODUCT = b.ID");
            }else{
                SELECT("a.ID, a.ID_FLASH_SALE, a.ID_PRODUCT, a.DISCOUNT_PRICE, a.SELL_QUANTITY, a.START_DATE, a.END_DATE, a.ADD_DISCOUNT, a.STATUS");
                FROM(TABLE_NAME + " a");
            }
            WHERE("a.IS_DELETED = " + 0);
            if (req.getIdFlashSale() != null && req.getIdFlashSale() != 0) {
                AND().WHERE("ID_FLASH_SALE = #{idFlashSale}");
            }
        }}.toString();
    }

    public String count(ProductFlashSaleDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
        }}.toString();
    }

    public String delete(Long id) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String deletes(ProductFlashSaleDto.DeleteReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("IS_DELETED = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }

    public String updateStatus(ProductFlashSaleDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            SET("UPDATED_BY = #{lastModifiedBy}");
            SET("UPDATED_DATE = now()");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(ProductFlashSale req) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("CREATED_BY, CREATED_DATE", "#{createdBy}, now()");
            VALUES("UPDATED_BY, UPDATED_DATE", "#{lastModifiedBy}, now()");
            VALUES("ID_FLASH_SALE ", "#{idFlashSale}");
            VALUES("ID_PRODUCT ", " #{idProduct}");
            VALUES("DISCOUNT_PRICE ", " #{discountPrice}");
            VALUES("SELL_QUANTITY ", " #{sellQuantity}");
            VALUES("START_DATE ", "#{startDate}");
            VALUES("END_DATE", "#{endDate}");
            VALUES("ADD_DISCOUNT ", " #{addDiscount}");
            VALUES("STATUS ", " #{status}");
        }}.toString();
    }

    public String update(ProductFlashSale req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("UPDATED_BY = #{lastModifiedBy}");
            SET("UPDATED_DATE = now()");
            SET("ID_FLASH_SALE = #{idFlashSale}");
            SET("ID_PRODUCT = #{idProduct}");
            SET("DISCOUNT_PRICE = #{discountPrice}");
            SET("SELL_QUANTITY = #{sellQuantity}");
            SET("START_DATE = #{startDate}");
            SET("END_DATE = #{endDate}");
            SET("ADD_DISCOUNT = #{addDiscount}");
            SET("STATUS = #{status}");
            WHERE("ID = #{id}");
        }}.toString();
    }
}
