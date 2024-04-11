package com.datn.watch.order.repository;

import com.pet.market.api.order.model.Order;
import com.pet.market.api.order.model.criteria.OrderCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderRepository {

    @SelectProvider(type= OrderSqlBuilder.class, method="findAll")
    List<Order> getAll(OrderCriteria criteria);

    @SelectProvider(type= OrderSqlBuilder.class, method="count")
    int count(OrderCriteria criteria);

    @Select("SELECT ID, CREATED_BY, ID_CUSTOMER customerId, PRICE, DISCOUNT_PRICE, IS_PAID, PAYMENT_TYPE, MARKET_PLACE, STATUS, DAY_RECEPTION FROM TB_ORDER WHERE ID = #{id}")
    Optional<Order> getOrder(Long id);

    @Insert("""
            INSERT INTO TB_ORDER
            (CREATED_BY, CREATED_DATE, DAY_RECEPTION, ID_CUSTOMER,
            PRICE, DISCOUNT_PRICE, IS_PAID, PAYMENT_TYPE, SHIPPING_UNIT, SHIPPING_CODE,
            MARKET_PLACE, STATUS)
            VALUES(#{createdBy}, CONVERT_TZ(NOW(), 'GMT', 'Asia/Ho_Chi_Minh'), CONVERT_TZ(NOW(), 'GMT', 'Asia/Ho_Chi_Minh'), #{customerId}, 
            #{price}, #{discountPrice}, #{isPaid}, #{paymentType}, #{shippingUnit}, #{shippingCode}, 
            #{marketPlace}, 0)
            """)
    @Options( useGeneratedKeys=true,keyProperty="id", keyColumn="id")
    long save(Order params);

    @Delete("UPDATE TB_ORDER SET IS_DELETE = 1 WHERE ID = #{id}")
    void delete(Long id);

    @UpdateProvider(type= OrderSqlBuilder.class, method="update")
    void update(Order params);

}
