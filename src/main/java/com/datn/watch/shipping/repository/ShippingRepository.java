package com.datn.watch.shipping.repository;

import com.pet.market.api.shipping.model.DeliveryProvider;
import com.pet.market.api.shipping.model.criteria.ShippingCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ShippingRepository {

    @SelectProvider(type= ShippingSqlBuilder.class, method="findAll")
    List<DeliveryProvider> getAll(ShippingCriteria criteria);

    @SelectProvider(type= ShippingSqlBuilder.class, method="count")
    int count(ShippingCriteria criteria);

    @Select("SELECT ID, CREATED_BY, CREATED_DATE, NAME, API_KEY, API_KEY_DEV, IMAGE, STATUS FROM TB_SHIPPING_PROVIDER WHERE ID = #{id}")
    Optional<DeliveryProvider> get(Long id);

    @Insert("""
            INSERT INTO TB_SHIPPING_PROVIDER
            (CREATED_BY, CREATED_DATE, NAME,
            API_KEY, API_KEY_DEV, IMAGE, STATUS)
            VALUES(#{createdBy}, CONVERT_TZ(NOW(), 'GMT', 'Asia/Ho_Chi_Minh'), #{name}, 
            #{apiKey}, #{apiKeyDev}, #{image}, 
            #{status})
            """)
    @Options( useGeneratedKeys=true,keyProperty="id", keyColumn="id")
    long save(DeliveryProvider params);

    @Delete("DELETE FROM TB_SHIPPING_PROVIDER WHERE ID = #{id}")
    void delete(Long id);

    @UpdateProvider(type= ShippingSqlBuilder.class, method="update")
    void update(DeliveryProvider params);

    @Select("SELECT id, API_KEY apiKey, API_KEY_DEV apiKeyDev, CUSTOMER_CODE customerCode, USER_NAME username, PASSWORD password FROM TB_SHIPPING_PROVIDER WHERE CODE = #{code} and status = 1")
    Optional<DeliveryProvider> findByCode(String code);
}
