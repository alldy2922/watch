package com.datn.watch.request.repository;

import com.pet.market.api.request.model.dto.RequestDto;
import com.pet.market.api.request.model.entity.Request;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;

public class RequestSqlBuilder {
    private static final String TABLE_NAME = "TB_REQUEST";

    public String getAll(RequestDto.ListReq req) {
        return new SQL() {{
            SELECT("`ID`, `TITLE`, `SUMMARY`, TYPE_ID, `TYPE`,`TYPE_NAME`, `METHOD`, `DEADLINE`, `COMMENT`, `STATUS`, CREATED_BY");
            FROM(TABLE_NAME);
            WHERE("is_deleted = " + 0);
            ORDER_BY("created_date DESC");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String getById(Long id) {
        return new SQL() {{
            SELECT("`ID`, `TITLE`, `SUMMARY`, TYPE_ID, `TYPE`,`TYPE_NAME`, `METHOD`, `DEADLINE`, `COMMENT`, `STATUS`, CREATED_BY");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String findAll(RequestDto.ListReq req) {
        return new SQL() {{
            SELECT("`ID`, `TITLE`, `SUMMARY`, TYPE_ID, `TYPE`,`TYPE_NAME`, `METHOD`, `DEADLINE`, `COMMENT`, `STATUS`, CREATED_BY");
            FROM(TABLE_NAME);
            WHERE("is_deleted = " + 0);
            ORDER_BY("created_date DESC");
        }}.toString();
    }

    public String count(RequestDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("is_deleted = " + 0);
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

    public String deletes(RequestDto.ListIdReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }

    public String updateStatus(RequestDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Request request) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("TITLE","#{title}");
            VALUES("SUMMARY","#{summary}");
            VALUES("TYPE","#{type}");
            VALUES("TYPE_ID","#{typeId}");
            VALUES("TYPE_NAME","#{typeName}");
            VALUES("METHOD","#{method}");
            VALUES("DEADLINE","#{deadline}");
            VALUES("COMMENT","#{comment}");
            VALUES("STATUS","#{status}");
        }}.toString();
    }

    public String update(Request object) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("TITLE = #{title}");
            SET("SUMMARY = #{summary}");
            SET("TYPE = #{type}");
            SET("TYPE_NAME = #{typeName}");
            SET("TYPE_ID = #{typeId}");
            SET("METHOD = #{method}");
            SET("DEADLINE = #{deadline}");
            SET("COMMENT = #{comment}");
            SET("STATUS = #{status}");
            WHERE("ID = #{id}");
        }}.toString();
    }
    public String updateWithTypeId(Request object) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("TITLE = #{title}");
            SET("SUMMARY = #{summary}");
            SET("TYPE = #{type}");
            SET("TYPE_NAME = #{typeName}");
            SET("METHOD = #{method}");
            SET("DEADLINE = #{deadline}");
            SET("COMMENT = #{comment}");
            SET("STATUS = #{status}");
            WHERE("TYPE_ID = #{typeId}");
        }}.toString();
    }
    public String approveStatus(RequestDto.ListIdReq req, Long status) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }
    public String reject(RequestDto.ListIdReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 2");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }
}
