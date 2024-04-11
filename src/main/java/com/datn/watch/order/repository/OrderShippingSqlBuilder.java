package com.datn.watch.order.repository;

import com.pet.market.api.order.model.OrderShipping;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import static com.pet.market.api.common.constant.TableConstant.TB_ORDER_SHIPPING;

public class OrderShippingSqlBuilder {

    public String update(OrderShipping params){
        return new SQL() {{
            UPDATE(TB_ORDER_SHIPPING);
            if(ObjectUtils.isNotEmpty(params.getAddress())){
                SET("ADDRESS = #{address}");
            }
            if(ObjectUtils.isNotEmpty(params.getProvince())){
                SET("PROVINCE = #{province}");
            }
            if(StringUtils.isNotBlank(params.getDistrict())){
                SET("DISTRICT = #{district}");
            }
            if(StringUtils.isNotBlank(params.getCommune())){
                SET("COMMUNE = #{commune}");
            }
            if(StringUtils.isNotBlank(params.getFullName())){
                SET("FULL_NAME = #{fullName}");
            }
            if(StringUtils.isNotBlank(params.getPhoneNumber())){
                SET("PHONE_NUMBER = #{phoneNumber}");
            }
            WHERE("ORDER_ID = #{id}");
        }}.toString();
    }

    public String delete(long id){
        return new SQL() {{
            DELETE_FROM(TB_ORDER_SHIPPING);
            WHERE("ORDER_ID = #{id}");
        }}.toString();
    }

}
