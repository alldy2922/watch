package com.datn.watch.shipping.repository;

import com.pet.market.api.shipping.model.DeliveryProvider;
import com.pet.market.api.shipping.model.criteria.ShippingCriteria;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

import static com.pet.market.api.common.constant.TableConstant.TB_SHIPPING_PROVIDER;

public class ShippingSqlBuilder {

    public String findAll(ShippingCriteria req){
        return new SQL() {{
            SELECT("ID, NAME, IMAGE, API_KEY, API_KEY_DEV, STATUS, CREATED_DATE");
            FROM(TB_SHIPPING_PROVIDER);
            WHERE(" 1 = 1 ");
            if(StringUtils.isNotBlank(req.getQuickSearch())){
                AND().WHERE("NAME like #{name}");
            }
            if ( Objects.nonNull(req.getStatus())){
                AND().WHERE("STATUS = #{status}");
            }
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(ShippingCriteria req){
        return new SQL() {{
            SELECT("COUNT(ID)");
            FROM(TB_SHIPPING_PROVIDER);
            WHERE("1 = 1");
            if(StringUtils.isNotBlank(req.getQuickSearch())){
                AND().WHERE("NAME like #{name}");
            }
            if ( Objects.nonNull(req.getStatus())){
                AND().WHERE("STATUS = #{status}");
            }
        }}.toString();
    }

    public String update(DeliveryProvider params){
        return new SQL() {{
           UPDATE(TB_SHIPPING_PROVIDER);
           if(ObjectUtils.isNotEmpty(params.getName())){
               SET("NAME = #{name}");
           }
           if(StringUtils.isNotEmpty(params.getImage())){
               SET("IMAGE = #{image}");
           }
           if(StringUtils.isNotBlank(params.getApiKey())){
               SET("API_KEY = #{apiKey}");
           }
            if(StringUtils.isNotBlank(params.getApiKeyDev())){
                SET("API_KEY_DEV = #{apiKeyDev}");
            }
           if(params.getStatus() != null){
               SET("STATUS = #{status}");
           }
           SET("updated_by = #{lastModifiedBy}");
           SET("updated_date = CONVERT_TZ(NOW(), 'GMT', 'Asia/Ho_Chi_Minh')");
           WHERE("id = #{id}");
        }}.toString();
    }

    public String selectAll(){
        return new SQL() {{
            SELECT("id, template_name name");
            FROM(TB_SHIPPING_PROVIDER);
            WHERE("status = 1");
        }}.toString();
    }

}
