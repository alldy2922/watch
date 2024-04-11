package com.datn.watch.customer.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.customer.model.CustomerAreaDto;
import com.pet.market.api.customer.model.CustomerDto;
import com.pet.market.api.customer.model.CustomerLisReq;
import com.pet.market.api.customer.model.UpdateStatusDto;
import com.pet.market.api.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("api v1 customer")
@Tag(name = "customer api v1")
@RequestMapping(Constant.API_USER_CUSTOMER)
public class CustomerController {
    private static final APILogger logger = new APILogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    @ApiOperation(value = "getCustomer", notes = "get all customer.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('CUSTOMER_V','CUSTOMER_E')")
    public Object getCustomer(@ModelAttribute CustomerLisReq req) {
        ResultPageData<List<CustomerDto>> listCustomer = customerService.findAll(req);
        logger.logApi(RequestMethod.GET, Constant.API_USER_CUSTOMER, req, listCustomer);
        return listCustomer;
    }

    @GetMapping("/active")
    @ApiOperation(value = "getCustomer", notes = "get active customer.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('CUSTOMER_V','CUSTOMER_E')")
    public Object listActiveEmployee(@ModelAttribute CustomerLisReq req) {
        req.setActive(true);
        ResultPageData<List<CustomerAreaDto>> listCustomer = customerService.listAll(req);
        logger.logApi(RequestMethod.GET, Constant.API_USER_CUSTOMER, req, listCustomer);
        return listCustomer;
    }

    @PatchMapping("")
    @ApiOperation(value = "updateStatus", notes = "update status.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('CUSTOMER_E')")
    public Object updateStatus(@RequestBody UpdateStatusDto rq) {
        try {
            customerService.updateStatus(rq);
            logger.logApi(RequestMethod.PATCH, Constant.API_USER_CUSTOMER, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @DeleteMapping("")
    @ApiOperation(value = "deleteCustomer", notes = "delete customer.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('CUSTOMER_E')")
    public Object deleteCustomer(@RequestParam List<String> ids) {
        try {
            customerService.delete(ids);
            logger.logApi(RequestMethod.DELETE, Constant.API_USER_CUSTOMER, ids, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PostMapping("")
    @ApiOperation(value = "saveAccount", notes = "save user.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('CUSTOMER_E')")
    public Object saveCustomer(@Valid @ModelAttribute CustomerDto rq) {
        Result result = new Result();
        try {
            result = customerService.saveOrUpdate(rq);
            logger.logApi(RequestMethod.POST, Constant.API_USER_CUSTOMER, rq, result);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getCustomerById", notes = "get customer by id.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('CUSTOMER_V','CUSTOMER_E')")
    public ResultData<CustomerDto> getCustomerById(@PathVariable String id) {
        ResultData<CustomerDto> resultData = new ResultData<>(customerService.findById(id));
        logger.logApi(RequestMethod.GET, Constant.API_USER_CUSTOMER, id, resultData);
        return resultData;
    }

    @PutMapping("")
    @ApiOperation(value = "updateCustomer", notes = "update customer.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('CUSTOMER_E')")
    public Object updateCustomer(@Valid @ModelAttribute CustomerDto rq) {
        Result result = new Result();
        try {
            result = customerService.saveOrUpdate(rq);
            logger.logApi(RequestMethod.PUT, Constant.API_USER_CUSTOMER, rq, result);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return result;
    }

}
