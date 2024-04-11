package com.datn.watch.salespromotion.productflashsale.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesCreated;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.salespromotion.productflashsale.model.dto.ProductFlashSaleDto;
import com.pet.market.api.salespromotion.productflashsale.service.ProductFlashSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api("Product Flash-Sale API v1")
@Tag(name = "Product Flash-Sale  API v1")
@RequestMapping(value = Constant.API_SALES_PROMOTION_PRODUCT_FLASH_SALE)
public class ProductFlashSaleRestController {

    private static final APILogger logger = new APILogger(ProductFlashSaleRestController.class);

    private final ProductFlashSaleService service;


    public ProductFlashSaleRestController(ProductFlashSaleService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "gets ", notes = "get all  .")
    @ApiResponsesOk()
    public Object gets(@Valid @ModelAttribute ProductFlashSaleDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_SALES_PROMOTION_PRODUCT_FLASH_SALE, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "get ", notes = "get one  .")
    @ApiResponsesOk()
    public ResultData<Object> get(@PathVariable Long id) throws Exception {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_SALES_PROMOTION_PRODUCT_FLASH_SALE + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add  ", notes = "add  .")
    public Object add(@RequestBody ProductFlashSaleDto.Request req) {
        service.save(req);
        logger.logApi(RequestMethod.POST, Constant.API_SALES_PROMOTION_PRODUCT_FLASH_SALE, req, null);
        return new Result();
    }

    @PatchMapping()
    @ApiOperation(value = "update  ", notes = "update  .")
    @ApiResponsesCreated()
    public Object update(@RequestBody ProductFlashSaleDto.Request req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_SALES_PROMOTION_PRODUCT_FLASH_SALE, req, null);
        return new Result();
    }

    @PatchMapping("/status")
    @ApiOperation(value = "update status  ", notes = "update status  .")
    @ApiResponsesCreated()
    public Object updateStatus(@RequestBody ProductFlashSaleDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_SALES_PROMOTION_PRODUCT_FLASH_SALE + "/status", req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    @ApiOperation(value = "delete  ", notes = "delete  .")
    public Object delete(@RequestBody ProductFlashSaleDto.DeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_SALES_PROMOTION_PRODUCT_FLASH_SALE, req, null);
        return new Result();
    }
}
