package com.datn.watch.customer.repository;

import com.pet.market.api.customer.model.CustomerAreaDto;
import com.pet.market.api.customer.model.CustomerLisReq;
import com.pet.market.api.customer.model.UpdateStatusDto;
import com.pet.market.api.customer.model.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CustomerRepository {
    @SelectProvider(type= CustomerSqlBuilder.class, method="findAll")
    List<Customer> findAll(CustomerLisReq req);
    @SelectProvider(type= CustomerSqlBuilder.class, method="listAll")
    List<CustomerAreaDto> listAll(CustomerLisReq req);

    @SelectProvider(type=CustomerSqlBuilder.class, method="count")
    int count(CustomerLisReq req);
    @DeleteProvider(type=CustomerSqlBuilder.class, method="delete")
    void delete(List<String> ids);
    @UpdateProvider(type=CustomerSqlBuilder.class, method="updateStatus")
    void updateStatus(UpdateStatusDto rq);
    @InsertProvider(type=CustomerSqlBuilder.class, method="save")
    void save(Customer customer);
    @SelectProvider(type=CustomerSqlBuilder.class, method="findById")
    Optional<Customer> findById(String id);
    @UpdateProvider(type=CustomerSqlBuilder.class, method="update")
    void update(Customer customer);
    @SelectProvider(type=CustomerSqlBuilder.class, method="checkPhoneNumber")
    int checkPhoneNumber(String id, String phoneNumber, Integer type);
}
