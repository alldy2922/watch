package com.datn.watch.customer.service;

import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.customer.model.CustomerAreaDto;
import com.pet.market.api.customer.model.CustomerDto;
import com.pet.market.api.customer.model.CustomerLisReq;
import com.pet.market.api.customer.model.UpdateStatusDto;
import com.pet.market.api.customer.model.entity.Customer;
import com.pet.market.api.customer.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public ResultPageData<List<CustomerDto>> findAll(CustomerLisReq req){
        int totalRecords = customerRepo.count(req);
        List<CustomerDto> listUser = ModelMapperUtils.mapAll(customerRepo.findAll(req), CustomerDto.class);
        return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, listUser);
    }

    public ResultPageData<List<CustomerAreaDto>> listAll(CustomerLisReq req){
        int totalRecords = customerRepo.count(req);
        List<CustomerAreaDto> listUser = customerRepo.listAll(req);
        return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, listUser);
    }

    @Transactional
    public void delete(List<String> ids){
        customerRepo.delete(ids);
    }

    public void updateStatus(UpdateStatusDto rq){
        customerRepo.updateStatus(rq);
    }

    @Transactional
    public Result saveOrUpdate(CustomerDto rq){
        Customer customer = ModelMapperUtils.map(rq, Customer.class);
        if(StringUtils.isBlank(customer.getId())){
            if(customerRepo.checkPhoneNumber(customer.getId(), customer.getPhoneNumber(), 0) > 0){
                return new Result("fail", "Số điện thoại đã tồn tại");
            }
            customerRepo.save(customer);
        }else{
            if(customerRepo.checkPhoneNumber(customer.getId(), customer.getPhoneNumber(), 1) > 0){
                return new Result("fail", "Số điện thoại đã tồn tại");
            }
            customerRepo.update(customer);
        }
        return new Result();
    }

    public CustomerDto findById(String id){
        Optional<Customer> customer = customerRepo.findById(id);
        if(customer.isPresent()){
            CustomerDto customerDto = ModelMapperUtils.map(customer.get(), CustomerDto.class);
            return customerDto;
        }
        return null;
    }
}
