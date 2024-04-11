package com.datn.watch.order.repository;

import com.pet.market.api.order.model.Order;
import com.pet.market.api.order.model.criteria.OrderCriteria;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.*;

public class OrderSqlBuilder {


    public String findAll(OrderCriteria req){
        return new SQL() {{
            SELECT("A.ID, A.ID_CUSTOMER as 'customerId', A.STATUS, A.MARKET_PLACE, A.IS_PAID, A.CREATED_DATE, A.PRICE, A.DISCOUNT_PRICE, A.PAYMENT_TYPE");
            FROM(TB_ORDER + " A").WHERE(" 1 = 1 ");
            if(StringUtils.isNotBlank(req.getCreatedBy())){
                AND().WHERE("A.CREATED_BY = #{createdBy}");
            }
            if(StringUtils.isNotBlank(req.getSearch())){
                AND().WHERE("(ID_CUSTOMER LIKE CONCAT(CONCAT('%',#{search}),'%') OR ID = #{search})");
            }

            if (!CollectionUtils.isEmpty(req.getStatus())){
                String param = StringUtils.join(req.getStatus(), ",");
                AND().WHERE("A.STATUS IN (" + param + ")");
            }
            if (ObjectUtils.isNotEmpty(req.getIsPaid())){
                AND().WHERE("A.IS_PAID = #{isPaid}");
            }
            if (StringUtils.isNotBlank(req.getMarketPlace())){
                AND().WHERE("A.MARKET_PLACE = #{marketPlace}");
            }
            if (ObjectUtils.isNotEmpty(req.getStartReception())){
                AND().WHERE("A.DAY_RECEPTION >= STR_TO_DATE(CONCAT(#{startReception}, ' 00:00:00'), '%Y-%m-%d %k:%i:%s')");
            }
            if (ObjectUtils.isNotEmpty(req.getEndReception())){
                AND().WHERE("A.DAY_RECEPTION <= STR_TO_DATE(CONCAT(#{endReception}, ' 23:59:59'), '%Y-%m-%d %k:%i:%s')");
            }
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(OrderCriteria req){
        return new SQL() {{
            SELECT("COUNT(ID)");
            FROM(TB_ORDER);
            WHERE("1 = 1");
            if(StringUtils.isNotBlank(req.getCreatedBy())){
                AND().WHERE("CREATED_BY = #{createdBy}");
            }

            if(StringUtils.isNotBlank(req.getSearch())){
                AND().WHERE("(ID_CUSTOMER LIKE CONCAT(CONCAT('%',#{search}),'%') OR ID = #{search})");
            }

            if (!CollectionUtils.isEmpty(req.getStatus())){
                String param = StringUtils.join(req.getStatus(), ",");
                AND().WHERE("STATUS in (" + param + ")");
            }
            if (ObjectUtils.isNotEmpty(req.getIsPaid())){
                AND().WHERE("IS_PAID = #{isPaid}");
            }
            if (ObjectUtils.isNotEmpty(req.getStartReception())){
                AND().WHERE("DAY_RECEPTION >= STR_TO_DATE(CONCAT(#{startReception}, ' 00:00:00'), '%Y-%m-%d %k:%i:%s')");
            }
            if (ObjectUtils.isNotEmpty(req.getEndReception())){
                AND().WHERE("DAY_RECEPTION <= STR_TO_DATE(CONCAT(#{endReception}, ' 23:59:59'), '%Y-%m-%d %k:%i:%s')");
            }
        }}.toString();
    }

    public String update(Order params){
        return new SQL() {{
            UPDATE(TB_ORDER);
            if(ObjectUtils.isNotEmpty(params.getStatus())){
                SET("STATUS = #{status}");
            }
            if(ObjectUtils.isNotEmpty(params.getIsPaid())){
                SET("IS_PAID = #{isPaid}");
            }
            if(StringUtils.isNotBlank(params.getReceiver())){
                SET("RECEIVER = #{receiver}");
                SET("DAY_RECEPTION = CONVERT_TZ(NOW(), 'GMT', 'Asia/Ho_Chi_Minh')");
            }
            if(ObjectUtils.isNotEmpty(params.getDeliveryDateShippingUnit())){
                SET("DELIVERY_DATE_SHIPPING_UNIT = #{deliveryDateShippingUnit}");
            }
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = CONVERT_TZ(NOW(), 'GMT', 'Asia/Ho_Chi_Minh')");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String delete(List<Long> ids){
        return new SQL() {{
            String param = StringUtils.join(ids, ",");
            DELETE_FROM(TB_ORDER);
            WHERE("id in ( "+param+" )");
        }}.toString();
    }

    public String selectAll(){
        return new SQL() {{
            SELECT("id, template_name name");
            FROM(TB_ORDER);
            WHERE("status = 1");
        }}.toString();
    }

}
