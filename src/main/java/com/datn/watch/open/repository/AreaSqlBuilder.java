package com.datn.watch.open.repository;

import com.pet.market.api.common.model.criteria.SearchCriteria;
import com.pet.market.api.open.model.criteria.AreaCriteria;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import static com.pet.market.api.common.constant.TableConstant.*;

public class AreaSqlBuilder {

    public String getAllProvince(SearchCriteria req){
        return new SQL() {{
            SELECT("ID, NAME as 'value'");
            FROM(TB_AREA_PROVINCE);
            WHERE(" 1 = 1 ");
            if(StringUtils.isNotBlank(req.getSearch())){
                AND().WHERE("NAME LIKE CONCAT(CONCAT('%', #{createdBy}), '%')");
            }
            ORDER_BY("NAME");
        }}.toString();
    }

    public String getAllDistrict(AreaCriteria req){
        return new SQL() {{
            SELECT("ID, NAME as 'value'");
            FROM(TB_AREA_DISTRICT);
            WHERE(" 1 = 1 ")
                    .AND().WHERE(" PROVINCE_ID = #{provinceId}");
            if(StringUtils.isNotBlank(req.getSearch())){
                AND().WHERE("NAME LIKE CONCAT(CONCAT('%', #{createdBy}), '%')");
            }
            ORDER_BY("NAME");
        }}.toString();
    }

    public String getAllCommune(AreaCriteria req){
        return new SQL() {{
            SELECT("ID, NAME as 'value'");
            FROM(TB_AREA_COMMUNE);
            WHERE(" 1 = 1 ")
                    .AND().WHERE(" DISTRICT_CODE = #{districtId}");
            if(StringUtils.isNotBlank(req.getSearch())){
                AND().WHERE("NAME LIKE CONCAT(CONCAT('%', #{createdBy}), '%')");
            }
            ORDER_BY("NAME");
        }}.toString();
    }

}
