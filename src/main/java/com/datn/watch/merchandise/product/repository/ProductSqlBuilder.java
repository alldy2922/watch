package com.datn.watch.merchandise.product.repository;

import com.pet.market.api.common.utils.StringUtils;
import com.pet.market.api.merchandise.product.model.dto.ProductDto;
import com.pet.market.api.merchandise.product.model.entity.Product;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;

public class ProductSqlBuilder {
    private static final String TABLE_NAME = "TB_PRODUCT";

    public String getAll(ProductDto.ListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, PRICE, THUMBNAIL_PC, THUMBNAIL_MO, IMAGE, UNIT, WEIGHT, INFO, PREFERENTIAL, STATUS, ID_CATEGORY, ID_BRAND, AMOUNT, `ATTRIBUTES`");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (req.getCategoryIds().length > 0) {
                AND().WHERE("ID_CATEGORY IN (" + Arrays.toString(req.getCategoryIds()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (req.getBrandIds().length > 0) {
                AND().WHERE("ID_BRAND IN (" + Arrays.toString(req.getBrandIds()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (req.getIsStops().length > 0) {
                AND().WHERE("IS_STOP IN (" + Arrays.toString(req.getIsStops()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }
            if (!StringUtils.isNullOrEmpty(req.getSortPrice())){
                ORDER_BY("price " + req.getSortPrice().toUpperCase());
            }
            AND().WHERE("is_deleted = " + 0);
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String getById(Long id) {
        return new SQL() {{
            SELECT(" ID, NAME, PRICE, THUMBNAIL_PC, THUMBNAIL_MO, IMAGE, UNIT, WEIGHT, INFO, PREFERENTIAL, STATUS, ID_CATEGORY, ID_BRAND, AMOUNT, `ATTRIBUTES`, HTML_IMAGE_DATA as htmlImageData, HTML_ATTR_DATA as htmlAttributesData");
            SELECT(" SHOPPE_LINK, TIKTOK_LINK, QR_CODE, SORT, IS_NEW, IS_BEST_SALE, IS_STOP");
            FROM(TABLE_NAME);
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String findAll(ProductDto.ListReq req) {
        return new SQL() {{
            SELECT("ID, NAME, PRICE, THUMBNAIL_PC, THUMBNAIL_MO, IMAGE, UNIT, WEIGHT, INFO, PREFERENTIAL, STATUS, ID_CATEGORY, ID_BRAND, AMOUNT, `ATTRIBUTES`");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (req.getCategoryIds().length > 0) {
                AND().WHERE("ID_CATEGORY IN (" + Arrays.toString(req.getCategoryIds()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (req.getBrandIds().length > 0) {
                AND().WHERE("ID_BRAND IN (" + Arrays.toString(req.getBrandIds()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (req.getIsStops().length > 0) {
                AND().WHERE("IS_STOP IN (" + Arrays.toString(req.getIsStops()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (!StringUtils.isNullOrEmpty(req.getStatus())) {
                AND().WHERE("status = "  + req.getStatus());
            }
            if (!StringUtils.isNullOrEmpty(req.getSortPrice())){
                ORDER_BY("price " + req.getSortPrice().toUpperCase());
            }
            AND().WHERE("is_deleted = " + 0);

        }}.toString();
    }

    public String count(ProductDto.ListReq req) {
        return new SQL() {{
            SELECT("count(id)");
            FROM(TABLE_NAME);
            WHERE("NAME LIKE CONCAT('%', #{query}, '%')");
            if (req.getCategoryIds().length > 0) {
                AND().WHERE("ID_CATEGORY IN (" + Arrays.toString(req.getCategoryIds()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (req.getBrandIds().length > 0) {
                AND().WHERE("ID_BRAND IN (" + Arrays.toString(req.getBrandIds()).replaceAll("[\\[\\] ]", "") + ")");
            }
            if (req.getIsStops().length > 0) {
                AND().WHERE("IS_STOP IN (" + Arrays.toString(req.getIsStops()).replaceAll("[\\[\\] ]", "") + ")");
            }
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
    public String deletes(ProductDto.DeleteReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = 0");
            SET("is_deleted = 1");
            WHERE("ID IN (" + Arrays.toString(req.getIds()).replaceAll("[\\[\\] ]", "") + ")");
        }}.toString();
    }
    public String updateStatus(ProductDto.StatusReq req) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Product object) {
        return new SQL() {{
            INSERT_INTO(TABLE_NAME);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("NAME, PRICE, THUMBNAIL_PC,THUMBNAIL_MO, IMAGE, UNIT, WEIGHT, INFO, PREFERENTIAL, STATUS, ID_CATEGORY, ID_BRAND, AMOUNT, `ATTRIBUTES`, HTML_IMAGE_DATA, HTML_ATTR_DATA",
                    "#{name}, #{price}, #{thumbnailPc}, #{thumbnailMo}, #{image}, #{unit}, #{weight}, #{info}, #{preferential}, #{status}, #{idCategory}, #{idBrand}, #{amount}, #{attributes}, #{htmlImageData}, #{htmlAttributesData}");
            VALUES("SHOPPE_LINK, TIKTOK_LINK", "#{shoppeLink},#{tiktokLink}");
            VALUES("QR_CODE, SORT", "#{qrCode},#{sort}");
            VALUES("IS_NEW, IS_BEST_SALE", "#{isNew},#{isBestSale}");
            VALUES("IS_STOP", "#{isStop}");

        }}.toString();
    }

    public String update(Product object) {
        return new SQL() {{
            UPDATE(TABLE_NAME);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");
            SET("NAME = #{name}");
            SET("THUMBNAIL_PC = #{thumbnailPc}");
            SET("THUMBNAIL_MO = #{thumbnailMo}");
            SET("IMAGE = #{image}");
            SET("UNIT = #{unit}");
            SET("WEIGHT = #{weight}");
            SET("INFO = #{info}");
            SET("PREFERENTIAL = #{preferential}");
            SET("STATUS = #{status}");
            SET("ID_CATEGORY = #{idCategory}");
            SET("ID_BRAND = #{idBrand}");
            SET("AMOUNT = #{amount}");
            SET("`ATTRIBUTES` = #{attributes}");
            SET("`HTML_ATTR_DATA` = #{htmlAttributesData}");
            SET("`HTML_IMAGE_DATA` = #{htmlImageData}");
            SET("`SHOPPE_LINK` = #{shoppeLink}");
            SET("`TIKTOK_LINK` = #{tiktokLink}");
            SET("`QR_CODE` = #{qrCode}");
            SET("`SORT` = #{sort}");
            SET("`IS_NEW` = #{isNew}");
            SET("`IS_BEST_SALE` = #{isBestSale}");
            SET("`IS_STOP` = #{isStop}");
            WHERE("ID = #{id}");
        }}.toString();
    }

}
