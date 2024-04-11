package com.datn.watch.order.controller;

import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.AuthUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.customer.model.CustomerDto;
import com.pet.market.api.customer.service.CustomerService;
import com.pet.market.api.order.model.criteria.OrderCriteria;
import com.pet.market.api.order.model.dto.OrderDTO;
import com.pet.market.api.order.model.dto.OrderDTO.OrderReq;
import com.pet.market.api.order.model.dto.OrderDTO.OrderRes;
import com.pet.market.api.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class ClientOrderController {

  private final OrderService service;

  private final CustomerService customerService;

  public ClientOrderController(OrderService service, CustomerService customerService) {
    this.service = service;
    this.customerService = customerService;
  }

  @Operation(summary="List advertisement")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List advertisement success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = List.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @GetMapping("")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResultPageData<List<OrderRes>> list(OrderCriteria criteria) {
    String userLogin = AuthUtils.getUserId();
    criteria.setCreatedBy(userLogin);

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
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResultData<OrderRes> get(@PathVariable("id") Long id) {
    ResultData<OrderRes> response = new ResultData<>();
    String userLogin = AuthUtils.getUserId();
    OrderRes order = service.get(id);
    if (userLogin.equals(order.getCreatedBy())) {
      response.setResultData(order);
    }
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
  public ResultData<Long> create(@Valid @RequestBody OrderDTO.OrderReq req) {
    ResultData<Long> response = new ResultData<>();
    CustomerDto res = customerService.findById(req.getReceiver().getPhoneNumber());
    if (ObjectUtils.isEmpty(res)) {
      OrderReq.Customer receiver = req.getReceiver();
      OrderReq.Address address = req.getAddress();
        CustomerDto dto = new CustomerDto(receiver.getName(), receiver.getPhoneNumber(), address.getProvince(),
          address.getDistrict(), address.getCommune(), address.getDetail());
      customerService.saveOrUpdate(dto);
    }

    response.setResultData(service.create(req));
    return response;
  }

}
