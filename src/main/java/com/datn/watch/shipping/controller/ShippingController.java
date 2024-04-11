package com.datn.watch.shipping.controller;

import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.shipping.model.criteria.ShippingCriteria;
import com.pet.market.api.shipping.model.dto.ShippingProviderDTO;
import com.pet.market.api.shipping.model.dto.ShippingProviderDTO.DeliveryProviderRes;
import com.pet.market.api.shipping.service.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/admin/shipping")
public class ShippingController {

  private final ShippingService service;

  public ShippingController(ShippingService service) {
    this.service = service;
  }

  @Operation(summary="List delivery provider")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List delivery provider success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = List.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @GetMapping("")
  public ResultPageData<List<DeliveryProviderRes>> list(@Validated ShippingCriteria criteria) {
    int total = service.count(criteria);
    ResultPageData<List<DeliveryProviderRes>> response = new ResultPageData<>(criteria, total);
    response.setResultData(service.list(criteria));
    return response;
  }

  @Operation(summary="Get delivery provider")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List order success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = DeliveryProviderRes.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @GetMapping("/detail/{id}")
  public ResultData<DeliveryProviderRes> get(@PathVariable("id") Long id) {
    ResultData<DeliveryProviderRes> response = new ResultData<>();
    //LoginRes a = vnPostService.login();
    response.setResultData(service.get(id));
    return response;
  }

  @Operation(summary="Create delivery provider")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Long.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @PostMapping("")
  public ResultData<Long> create(@Valid @RequestBody ShippingProviderDTO.DeliveryProviderReq req) {
    ResultData<Long> response = new ResultData<>();
    response.setResultData(service.create(req));
    return response;
  }

  @Operation(summary="Create delivery provider")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Long.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @PatchMapping("/{id}")
  public Result update(@PathVariable("id") Long id, @RequestBody Map<String, Object> params) {
    Result response = new Result();
    service.update(id, params);
    return response;
  }

}
