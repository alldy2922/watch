package com.datn.watch.salespromotion.flashsale.repository;

import com.pet.market.api.common.utils.StringUtils;
import com.pet.market.api.salespromotion.flashsale.model.dto.FlashSaleDto;
import com.pet.market.api.salespromotion.flashsale.model.entity.FlashSale;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;

public class FlashSaleSqlBuilder {
    private static final String TABLE_NAME = "TB_FLASH_SALE";

    public String getAll(FlashSaleDto.ListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, IMG_BANNER, START_DATE, END_DATE, STATUS, APPROVE_STATUS");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = " + req.getStatus());
            }
            AND().WHERE("is_deleted = " + 0);
            ORDER_BY("START_DATE");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String getById(Long id) {
        return new SQL() {{
            SELECT("ID, NAME, IMG_BANNER, START_DATE, END_DATE, STATUS, APPROVE_STATUS");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }

    public String findAll(FlashSaleDto.ListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, IMG_BANNER, START_DATE, END_DATE, STATUS, APPROVE_STATUS");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = " + req.getStatus());
            }
            AND().WHERE("is_deleted = " + 0);
            ORDER_BY("START_DATE");
        }}.toString();
    }

    public String count(FlashSaleDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = " + req.getStatus());
            }
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }

    public String delete(Long id) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String deletes(FlashSaleDto.DeleteReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }

    public String updateStatus(FlashSaleDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String approveStatus(FlashSaleDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("APPROVE_STATUS = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String save(FlashSale flashSale) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("NAME ", "#{name}");
            VALUES("IMG_BANNER ", " #{imgBanner}");
            VALUES("START_DATE ", "#{startDate}" );
            VALUES("END_DATE", "#{endDate}");
            VALUES("STATUS ", " #{status}");
        }}.toString();
    }

    public String update(FlashSale object) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("NAME = #{name}");
            if (!StringUtils.isNullOrEmpty(object.getImgBanner())){
                SET("IMG_BANNER = #{imgBanner}");
            }
            SET("START_DATE = #{startDate}");
            SET("END_DATE = #{endDate}");
            SET("STATUS = #{status}");
            WHERE("ID = #{id}");
        }}.toString();
    }
    public String findAllWithinDate(FlashSale flashSale) {
        return new SQL() {{
            SELECT("ID, NAME, IMG_BANNER, START_DATE, END_DATE, STATUS");
            FROM(TABLE_NAME);
            WHERE("START_DATE < #{endDate} and END_DATE > #{startDate}");
            AND().WHERE("status = " + 1);
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }
}
