package com.datn.watch.merchandise.product.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesCreated;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.merchandise.product.model.dto.ProductDto;
import com.pet.market.api.merchandise.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api(" API v1  Product")
@Tag(name = "Product API v1")
@RequestMapping(value = Constant.API_MERCHANDISE_PRODUCT)
public class ProductRestController {

    private static final APILogger logger = new APILogger(ProductRestController.class);

    private final ProductService service;


    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "getProduct", notes = "get all Product.")
    @ApiResponsesOk()
    public Object getProduct(@Valid @ModelAttribute ProductDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_PRODUCT, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getProduct", notes = "get one Product.")
    @ApiResponsesOk()
    public ResultData<Object> getProduct(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_PRODUCT + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add Product", notes = "add Product.")
    @ApiResponsesCreated()
    public Object addProduct(@RequestBody ProductDto.Request req) {
        service.save(req);
        logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_PRODUCT, req, null);
        return new Result();
    }

    @PatchMapping()
    @ApiOperation(value = "update Product", notes = "update Product.")
    @ApiResponsesCreated()
    public Object updateProduct(@RequestBody ProductDto.Request req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_PRODUCT, req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    @ApiOperation(value = "delete Product", notes = "delete Product.")
    public Object delete(@RequestBody ProductDto.DeleteReq id) {
        service.delete(id);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_PRODUCT, id, null);
        return new Result();
    }
    @PatchMapping("/status")
    @ApiOperation(value = "update status Product", notes = "update status Product.")
    @ApiResponsesCreated()
    public Object updateStatus(@RequestBody ProductDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_PRODUCT + "/status", req, null);
        return new Result();
    }
}
