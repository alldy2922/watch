package com.datn.watch.merchandise.category.repository;

import com.pet.market.api.common.utils.StringUtils;
import com.pet.market.api.merchandise.category.model.dto.CategoryDto;
import com.pet.market.api.merchandise.category.model.entity.Category;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;
import java.util.Objects;

public class CategorySqlBuilder {
    private static final String TB_CATEGORY = "TB_CATEGORY";
    public String getAll(CategoryDto.ListReq req) {
        return new SQL() {{
            SELECT(" ID, NAME, `LEVEL`, PARENT, LINK_PAGE as linkPage, URL, `ATTRIBUTES`, `HAS_TOP` as hasTop, BANNER, 'BANNER_PC' as bannerPc, STATUS, HTML_DATA, ICON_URL, SORT");
            FROM(TB_CATEGORY);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }if (Objects.nonNull(req.getLevel())) {
                AND().WHERE("level = "  + req.getLevel());
            }
            AND().WHERE("is_deleted = " + 0);
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }
    public String getById(Long id) {
        return new SQL() {{
            SELECT(" ID, NAME, `LEVEL`, PARENT, LINK_PAGE as linkPage, URL, `ATTRIBUTES`, `HAS_TOP` as hasTop, BANNER, BANNER_PC as bannerPc, STATUS, HTML_DATA as htmlData, ICON_URL, SORT");
            FROM(TB_CATEGORY);
            WHERE("ID = #{id}");
        }}.toString();
    }
    public String findAll(CategoryDto.ListReq req) {
        return new SQL() {{
            SELECT(" ID, NAME, `LEVEL`, PARENT, LINK_PAGE as linkPage, URL, `ATTRIBUTES`, `HAS_TOP` as hasTop, BANNER, BANNER_PC as bannerPc, STATUS, HTML_DATA, ICON_URL, SORT");
            FROM(TB_CATEGORY);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }if (Objects.nonNull(req.getLevel())) {
                AND().WHERE("level = "  + req.getLevel());
            }
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }

    public String count(CategoryDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_CATEGORY);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }
            if (Objects.nonNull(req.getLevel())) {
                AND().WHERE("level = "  + req.getLevel());
            }
            AND().WHERE("is_deleted = " + 0);
        }}.toString();
    }

    public String delete(Long id) {
        return new SQL() {{
            UPDATE(TB_CATEGORY);
            SET("status = 0");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String deletes(CategoryDto.DeleteReq req) {
        return new SQL() {{
            UPDATE(TB_CATEGORY);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }
    public String updateStatus(CategoryDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TB_CATEGORY);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String save(Category object) {
        return new SQL() {{
            INSERT_INTO(TB_CATEGORY);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("NAME, LEVEL, PARENT, LINK_PAGE, URL, ATTRIBUTES, HAS_TOP, BANNER, BANNER_PC, STATUS, HTML_DATA",
                    "#{name}, #{level}, #{parent}, #{linkPage}, #{url}, #{attributes}, #{hasTop}, #{banner}, #{bannerPc}, #{status}, #{htmlData}");
            VALUES("ICON_URL", "#{iconUrl}");
            VALUES("SORT", "#{sort}");
        }}.toString();
    }
    public String update(Category object) {
        return new SQL() {{
            UPDATE(TB_CATEGORY);
            SET("created_by = #{createdBy}");
            SET("created_date = now()");
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("NAME = #{name}");
            SET("LEVEL = #{level}");
            SET("PARENT = #{parent}");
            SET("LINK_PAGE = #{linkPage}");
            SET("URL = #{url}");
            SET("ATTRIBUTES = #{attributes}");
            SET("HAS_TOP = #{hasTop}");
            SET("BANNER = #{banner}");
            SET("BANNER_PC = #{bannerPc}");
            SET("STATUS = #{status}");
            SET("HTML_DATA = #{htmlData}");
            SET("ICON_URL = #{iconUrl}");
            SET("SORT = #{sort}");

            WHERE("ID = #{id}");
        }}.toString();
    }
    public String getAttributes(CategoryDto.IdsReq req) {
        return new SQL() {{
            SELECT(" ID,PARENT, `ATTRIBUTES`");
            FROM(TB_CATEGORY);
            WHERE("ID IN (" +Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "")  + ")");
        }}.toString();
    }

}
