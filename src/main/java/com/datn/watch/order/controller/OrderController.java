package com.datn.watch.order.controller;

import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.customer.model.CustomerDto;
import com.pet.market.api.customer.service.CustomerService;
import com.pet.market.api.order.model.criteria.OrderCriteria;
import com.pet.market.api.order.model.dto.OrderDTO;
import com.pet.market.api.order.model.dto.OrderDTO.OrderRes;
import com.pet.market.api.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/admin/order")
public class OrderController {

  private final OrderService service;

  private final CustomerService customerService;


  public OrderController(OrderService service, CustomerService customerService) {
    this.service = service;
    this.customerService = customerService;
  }

  @Operation(summary="List product order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List order success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = List.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @GetMapping("")
  public ResultPageData<List<OrderRes>> list(OrderCriteria criteria) {
    int total = service.count(criteria);
    ResultPageData<List<OrderRes>> response = new ResultPageData<>(criteria, total);
    response.setResultData(service.list(criteria));
    return response;
  }

  @Operation(summary="Get product order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List order success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = OrderRes.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @GetMapping("/detail/{id}")
  public ResultData<OrderRes> get(@PathVariable("id") Long id) {
    ResultData<OrderRes> response = new ResultData<>();
    response.setResultData(service.get(id));
    return response;
  }

  @PatchMapping("/{id}")
  public Result update(@PathVariable("id") Long id,
                       @RequestBody Map<String, Object> params) {
    Result response = new Result();
    service.update(id, params);
    return response;
  }

  @PatchMapping("/confirm/{id}")
  public Result confirm(@PathVariable("id") Long id) {
    Result response = new Result();
    service.recepict(id, true);
    return response;
  }

  @DeleteMapping("/{id}")
  public Result cancel(@PathVariable("id") Long id) {
    Result response = new Result();
    service.recepict(id, false);
    return response;
  }

  @Operation(summary="Create order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "success",
                  content = { @Content(mediaType = "application/json",
                          schema = @Schema(implementation = Long.class)) }),
          @ApiResponse(responseCode = "400", description = "Invalid request",
                  content = @Content)})
  @PostMapping("")
  public ResultData<Long> create(@Valid @RequestBody OrderDTO.CreateOrderReq req) {
    ResultData<Long> response = new ResultData<>();
    OrderDTO.OrderReq request = new OrderDTO.OrderReq();
    BeanUtils.copyProperties(req, request);

    OrderDTO.OrderReq.Customer receiver = new OrderDTO.OrderReq.Customer();
    receiver.setName(req.getFullName());
    receiver.setPhoneNumber(req.getPhoneNumber());
    request.setReceiver(receiver);

    OrderDTO.OrderReq.Address address = new OrderDTO.OrderReq.Address("VN", req.getProvince(), req.getDistrict(), req.getCommune(), req.getDetail());
    request.setAddress(address);

    CustomerDto res = customerService.findById(request.getReceiver().getPhoneNumber());
    if (ObjectUtils.isEmpty(res)) {
      CustomerDto dto = new CustomerDto(receiver.getName(), receiver.getPhoneNumber(), address.getProvince(),
              address.getDistrict(), address.getCommune(), address.getDetail());
      customerService.saveOrUpdate(dto);
    }

    response.setResultData(service.create(request));
    return response;
  }

}
