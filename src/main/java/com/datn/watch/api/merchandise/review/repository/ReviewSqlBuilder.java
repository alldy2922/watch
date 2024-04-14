package com.datn.watch.api.merchandise.review.repository;

import org.apache.ibatis.jdbc.SQL;

import com.datn.watch.api.merchandise.review.model.dto.ReviewDto;
import com.datn.watch.api.merchandise.review.model.entity.Review;

import java.util.Arrays;

public class ReviewSqlBuilder {
    private static final String TABLE_NAME = "TB_REVIEW";

    public String getAll(ReviewDto.ListReq req) {
        return new SQL() {{
            SELECT("`ID`, `NAME`, `NOTE`, `STAR`, `LIKE_CNT`, `CONTENT`, `TYPE_PEOPLE`, `STATUS`, `IMAGE`, `ID_PRODUCT`, `CREATED_DATE`, `DATE_OF_REVIEW`");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            AND().WHERE("is_deleted = " + 0);
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String getById(Long id) {
        return new SQL() {{
            SELECT("`ID`, `NAME`, `NOTE`, `STAR`, `LIKE_CNT`, `CONTENT`, `TYPE_PEOPLE`, `STATUS`, `IMAGE`, `ID_PRODUCT`, `CREATED_DATE`, `DATE_OF_REVIEW`, `HTML_IMAGE_DATA`");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String findAll(ReviewDto.ListReq req) {
        return new SQL() {{
            SELECT("`ID`, `NAME`, `NOTE`, `STAR`, `LIKE_CNT`, `CONTENT`, `TYPE_PEOPLE`, `STATUS`, `IMAGE`, `ID_PRODUCT`, `CREATED_DATE`, `DATE_OF_REVIEW`");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }

    public String count(ReviewDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
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

    public String deletes(ReviewDto.DeleteReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }

    public String updateStatus(ReviewDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Review object) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("`NAME`, `NOTE`, `STAR`, `LIKE_CNT`, `CONTENT`, `TYPE_PEOPLE`, `STATUS`, `IMAGE`, `ID_PRODUCT`, `DATE_OF_REVIEW`, `HTML_IMAGE_DATA`",
                    "#{name}, #{note}, #{star}, #{likeCnt}, #{content}, #{typePeople}, 0, #{image}, #{idProduct}, #{dateOfReview}, #{htmlImageData}");
        }}.toString();
    }

    public String update(Review object) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("NAME = #{name}");
            SET("NOTE = #{note}");
            SET("STAR = #{star}");
            SET("LIKE_CNT = #{likeCnt}");
            SET("CONTENT = #{content}");
            SET("TYPE_PEOPLE = #{typePeople}");
            SET("STATUS = #{status}");
            SET("IMAGE = #{image}");
            SET("ID_PRODUCT = #{idProduct}");
            SET("DATE_OF_REVIEW = #{dateOfReview}");
            SET("HTML_IMAGE_DATA = #{htmlImageData}");
            WHERE("ID = #{id}");
        }}.toString();
    }

}
