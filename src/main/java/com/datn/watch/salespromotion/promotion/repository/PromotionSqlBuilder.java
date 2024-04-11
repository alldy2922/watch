package com.datn.watch.salespromotion.promotion.repository;

import com.pet.market.api.common.utils.StringUtils;
import com.pet.market.api.merchandise.review.model.dto.ReviewDto;
import com.pet.market.api.salespromotion.flashsale.model.entity.FlashSale;
import com.pet.market.api.salespromotion.promotion.model.dto.PromotionDto;
import com.pet.market.api.salespromotion.promotion.model.entity.Promotion;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;

public class PromotionSqlBuilder {
    private static final String TABLE_NAME = "TB_PROMOTION";

    public String getAll(PromotionDto.ListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, TYPE, START_DATE, END_DATE, UN_LIMIT_TIME, STATUS, APPROVE_STATUS, VALUE");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = " + req.getStatus());
            }
            AND().WHERE("IS_DELETED = " + 0);
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String getById(Long id) {
        return new SQL() {{
            SELECT("ID, NAME, TYPE, START_DATE, END_DATE, UN_LIMIT_TIME, STATUS, APPROVE_STATUS, VALUE");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String findAll(PromotionDto.ListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, TYPE, START_DATE, END_DATE, UN_LIMIT_TIME, STATUS, APPROVE_STATUS, VALUE");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = " + req.getStatus());
            }
            AND().WHERE("IS_DELETED = " + 0);
        }}.toString();
    }

    public String count(PromotionDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = " + req.getStatus());
            }
            AND().WHERE("IS_DELETED = " + 0);
        }}.toString();
    }

    public String delete(Long id) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String deletes(PromotionDto.DeleteReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ") AND APPROVE_STATUS <> 1");
        }}.toString();
    }
    public String updateStatus(PromotionDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Promotion object) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("NAME, TYPE, START_DATE, END_DATE", "#{name}, #{type}, #{startDate}, #{endDate}");
            VALUES("UN_LIMIT_TIME, STATUS", "#{unLimitTime}, #{status}");
            VALUES("VALUE", "#{value}");
            VALUES("APPROVE_STATUS", "0");
        }}.toString();
    }
    public String update(Promotion object) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("NAME = #{name}");
            SET("TYPE = #{type}");
            SET("START_DATE = #{startDate}");
            SET("END_DATE = #{endDate}");
            SET("UN_LIMIT_TIME = #{unLimitTime}");
            SET("STATUS = #{status}");
            SET("APPROVE_STATUS = #{approveStatus}");
            SET("VALUE = #{value}");
            WHERE("ID = #{id} and APPROVE_STATUS <> 1");
        }}.toString();
    }
    public String getPromotionActive(PromotionDto.ListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, TYPE, VALUE");
            FROM(TABLE_NAME);
            WHERE("1 = 1");
            AND().WHERE("IS_DELETED = 0");
            AND().WHERE("APPROVE_STATUS = 1");
            AND().WHERE("STATUS = 1");
            AND().WHERE("START_DATE < now()");
            AND().WHERE("END_DATE > now()");
            ORDER_BY("created_date");
        }}.toString();
    }

}
