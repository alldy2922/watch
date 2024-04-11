
package com.datn.watch.order.repository;

import com.pet.market.api.order.model.OrderShipping;
import com.pet.market.api.order.model.dto.OrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Optional;

@Mapper
public interface OrderShippingRepository {

    @Select("SELECT A.ID_CUSTOMER, A.FULL_NAME, A.PHONE_NUMBER, B.ID AS PROVINCE, B.NAME AS PROVINCENAME, C.ID AS DISTRICT, C.NAME AS DISTRICTNAME, D.ID AS COMMUNE, D.NAME AS COMMUNENAME, A.ADDRESS FROM TB_ORDER_SHIPPING_INFO A " +
            "INNER JOIN TB_AREA_PROVINCE B ON A.PROVINCE = B.ID " +
            "INNER JOIN TB_AREA_DISTRICT C ON A.DISTRICT = C.ID " +
            "INNER JOIN TB_AREA_COMMUNE D ON A.COMMUNE = D.ID " +
            " WHERE ORDER_ID = #{id}")
    Optional<OrderShipping> get(long id);

    @Insert("""
            INSERT INTO TB_ORDER_SHIPPING_INFO
            (ORDER_ID, ID_CUSTOMER, FULL_NAME,
            GENDER, PHONE_NUMBER, ADDRESS, PROVINCE, DISTRICT, COMMUNE)
            VALUES(#{orderId}, #{customer.phoneNumber}, #{customer.name}, #{customer.isMale}, 
            #{customer.phoneNumber}, #{address.detail}, #{address.province}, #{address.district}, #{address.commune})
            """)
    long save(OrderDTO.OrderReq.Customer customer, OrderDTO.OrderReq.Address address, long orderId);

    @UpdateProvider(type= OrderSqlBuilder.class, method="delete")
    void delete(Long id);

    @UpdateProvider(type= OrderShippingSqlBuilder.class, method="update")
    void update(OrderShipping params);

}
