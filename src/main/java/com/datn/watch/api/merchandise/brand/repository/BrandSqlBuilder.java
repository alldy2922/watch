package com.datn.watch.api.merchandise.brand.repository;

import com.datn.watch.common.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.datn.watch.api.merchandise.brand.model.dto.BrandDto;
import com.datn.watch.api.merchandise.brand.model.entity.Brand;

import java.util.Arrays;

public class BrandSqlBuilder {
    private static final String TABLE_NAME = "TB_BRAND";
    public String getAll(BrandDto.BrandListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, SORT, IMAGE, STATUS");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }
            AND().WHERE("is_deleted = " + 0);
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }
    public String getById(Long id) {
        return new SQL() {{
            SELECT("ID, NAME, SORT, IMAGE, STATUS");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
        }}.toString();
    }
    public String findAll(BrandDto.BrandListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, SORT, IMAGE, STATUS");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }

    public String count(BrandDto.BrandListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }

    public String delete(Long id) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String deletes(BrandDto.BrandDeleteReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }
    public String updateStatus(BrandDto.BrandStatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String save(Brand object) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("NAME, SORT, IMAGE, STATUS", "#{name}, #{sort}, #{image}, #{status}");
        }}.toString();
    }

}
