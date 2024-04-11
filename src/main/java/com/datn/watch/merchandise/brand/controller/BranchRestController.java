package com.datn.watch.merchandise.brand.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesCreated;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.merchandise.brand.model.dto.BrandDto;
import com.pet.market.api.merchandise.brand.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api(" API v1  Brand")
@Tag(name = "Brand API v1")
@RequestMapping(value = Constant.API_MERCHANDISE_BRAND)
public class BranchRestController {

    private static final APILogger logger = new APILogger(BranchRestController.class);

    private final BrandService service;


    public BranchRestController(BrandService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "getBrand", notes = "get all Brand.")
    @ApiResponsesOk()
    public Object gets(
            @Valid @ModelAttribute BrandDto.BrandListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_BRAND, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getBrand", notes = "get one Brand.")
    @ApiResponsesOk()
    public ResultData<Object> get(@PathVariable Long id) throws Exception {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_BRAND + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add Brand", notes = "add Brand.")
    @ApiResponsesCreated()
    public Object add(@ModelAttribute BrandDto.BrandReq req) {
        try {
            service.save(req);
            logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_BRAND, req, null);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PatchMapping()
    @ApiOperation(value = "update Brand", notes = "update Brand.")
    @ApiResponsesCreated()
    public Object update(@ModelAttribute BrandDto.BrandReq req) {
        try {
            service.update(req);
            logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_BRAND, req, null);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }
    @PatchMapping("/status")
    @ApiOperation(value = "update status Brand", notes = "update status Brand.")
    @ApiResponsesCreated()
    public Object updateStatus(@RequestBody BrandDto.BrandStatusReq req) {
            service.updateStatus(req);
            logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_BRAND + "/status", req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    @ApiOperation(value = "delete Brand", notes = "delete Brand.")
    public Object delete(@RequestBody BrandDto.BrandDeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_BRAND , req, null);
        return new Result();
    }
}
