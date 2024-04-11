package com.datn.watch.shipping.controller;

import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.interlock.shipping.model.vnpost.CancelOrderReq;
import com.pet.market.api.interlock.shipping.model.vnpost.FeeOrderReq;
import com.pet.market.api.interlock.shipping.model.vnpost.OrderCreationReq;
import com.pet.market.api.interlock.shipping.model.vnpost.UpdateOrderReq;
import com.pet.market.api.interlock.shipping.vnPost.VnPostService;
import com.pet.market.api.shipping.model.dto.VnPostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/shipping/vn-post")
public class VnPostController {

  private final VnPostService vnPostService;

  public VnPostController(VnPostService vnPostService) {
    this.vnPostService = vnPostService;
  }

  @Operation(summary="Create domestic order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Long.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @PostMapping("")
  public Object create(@Valid @RequestBody OrderCreationReq req) {
    ResultData<Object> response = new ResultData<>();
    try{
      response.setResultData(vnPostService.createDomesticOrder(req));
    }
    catch (Exception e){
      return new Result("fail",e.getMessage());
    }
    return response;
  }

  @Operation(summary="Update domestic order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Long.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @PutMapping("")
  public Object update(@Valid @RequestBody UpdateOrderReq req) {
    ResultData<Object> response = new ResultData<>();
    try{
      response.setResultData(vnPostService.updateDomesticOrder(req));
    }
    catch (Exception e){
      return new Result("fail",e.getMessage());
    }
    return response;
  }

  @Operation(summary="Update domestic order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Long.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @PostMapping("/fee/calculate")
  public Object calculateFee(@Valid @RequestBody FeeOrderReq req) {
    ResultData<Object> response = new ResultData<>();
    try{
      response.setResultData(vnPostService.calculateFee(req));
    }
    catch (Exception e){
      return new Result("fail",e.getMessage());
    }
    return response;
  }

  @Operation(summary="Cancel domestic order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "success",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Long.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid request",
          content = @Content)})
  @PostMapping("/order/cancel")
  public Object cancelShipping(@Valid @RequestBody CancelOrderReq req) {
    ResultData<Object> response = new ResultData<>();
    try{
      response.setResultData(vnPostService.cancelOrder(req));
    }
    catch (Exception e){
      return new Result("fail",e.getMessage());
    }
    return response;
  }

  @GetMapping("/spdv")
  public ResultData<List<VnPostDTO.Spdv>> getAllSpdv() {
    ResultData<List<VnPostDTO.Spdv>> response = new ResultData<>();
    response.setResultData(vnPostService.getAllSpdv());
    return response;
  }

  @GetMapping("/spdv/findByServiceCode")
  public ResultData<List<VnPostDTO.Gtgt>> getAllGtgt(@RequestParam("serviceCode") String serviceCode) {
    ResultData<List<VnPostDTO.Gtgt>> response = new ResultData<>();
    response.setResultData(vnPostService.getAllGtgt(serviceCode));
    return response;
  }

  @GetMapping("/gtgt/findByServiceCode")
  public ResultData<List<VnPostDTO.Prop>> getAllProp(@RequestParam("serviceCode") String serviceCode) {
    ResultData<List<VnPostDTO.Prop>> response = new ResultData<>();
    response.setResultData(vnPostService.getAllProp(serviceCode));
    return response;
  }

}


