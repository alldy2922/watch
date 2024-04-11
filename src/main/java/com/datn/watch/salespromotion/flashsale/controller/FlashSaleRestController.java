package com.datn.watch.salespromotion.flashsale.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesCreated;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.salespromotion.flashsale.model.dto.FlashSaleDto;
import com.pet.market.api.salespromotion.flashsale.service.FlashSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api("Flash-Sale API v1")
@Tag(name = "Flash-Sale  API v1")
@RequestMapping(value = Constant.API_SALES_PROMOTION_FLASH_SALE)
public class FlashSaleRestController {

    private static final APILogger logger = new APILogger(FlashSaleRestController.class);

    private final FlashSaleService service;


    public FlashSaleRestController(FlashSaleService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "get ", notes = "get all  .")
    @ApiResponsesOk()
    public Object get(
            @Valid @ModelAttribute FlashSaleDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_SALES_PROMOTION_FLASH_SALE, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "get ", notes = "get one  .")
    @ApiResponsesOk()
    public ResultData<Object> get(@PathVariable Long id) throws Exception {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_SALES_PROMOTION_FLASH_SALE + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add  ", notes = "add  .")
    public Object add(@RequestBody FlashSaleDto.Request req) {
        ResultData<Object> resultData = new ResultData<>();
        long id = service.save(req);
        resultData.setResultData(id);
        logger.logApi(RequestMethod.POST, Constant.API_SALES_PROMOTION_FLASH_SALE, req, resultData);
        return resultData;
    }

    @PatchMapping()
    @ApiOperation(value = "update  ", notes = "update  .")
    @ApiResponsesCreated()
    public Object update(@RequestBody FlashSaleDto.Request req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_SALES_PROMOTION_FLASH_SALE, req, null);
        return new Result();
    }

    @PatchMapping("/status")
    @ApiOperation(value = "update status  ", notes = "update status  .")
    @ApiResponsesCreated()
    public Object updateStatus(@RequestBody FlashSaleDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_SALES_PROMOTION_FLASH_SALE + "/status", req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    @ApiOperation(value = "delete  ", notes = "delete  .")
    public Object delete(@RequestBody FlashSaleDto.DeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_SALES_PROMOTION_FLASH_SALE, req, null);
        return new Result();
    }
}
