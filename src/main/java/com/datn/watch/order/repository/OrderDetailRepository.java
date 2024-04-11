package com.datn.watch.order.repository;

import com.pet.market.api.order.model.OrderItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailRepository {

    @Select({"""
                SELECT A.ID, A.ID_ORDER, A.ID_PRODUCT productId, A.PRICE, A.AMOUNT quantity, B.NAME
                FROM TB_ORDER_DETAIL A LEFT JOIN TB_PRODUCT B ON A.ID_PRODUCT = B.ID
                WHERE ID_ORDER = #{orderId}
            """})
    List<OrderItem> getAll(long orderId);

    @Insert({"""
            INSERT INTO TB_ORDER_DETAIL (ID_ORDER, ID_PRODUCT, PRICE, AMOUNT)
            VALUES
            			(
            			#{orderId}
            			, #{productId}
            			, #{price}
            			, #{quantity}
            			)
            """})
    long save(OrderItem params);

    @Delete("DELETE FROM TB_ORDER_DETAIL WHERE ID_ORDER = #{orderId}")
    void delete(Long orderId);

}
