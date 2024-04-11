package com.datn.watch.salespromotion.promotion.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.salespromotion.promotion.model.dto.PromotionDto;
import com.pet.market.api.salespromotion.promotion.service.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api("Promotion API v1  ")
@Tag(name = "Promotion API v1")
@RequestMapping(value = Constant.API_SALES_PROMOTION_PROMOTION)
public class PromotionRestController {

    private static final APILogger logger = new APILogger(PromotionRestController.class);

    private final PromotionService service;


    public PromotionRestController(PromotionService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "getPromotion", notes = "get all Promotion.")
    public Object gets(
            @Valid @ModelAttribute PromotionDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_SALES_PROMOTION_PROMOTION, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getPromotion", notes = "get one Promotion.")
    public ResultData<Object> get(@PathVariable Long id) throws Exception {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_SALES_PROMOTION_PROMOTION + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add Promotion", notes = "add Promotion.")
    public Object add(@RequestBody PromotionDto.Request req) {
        service.save(req);
        logger.logApi(RequestMethod.POST, Constant.API_SALES_PROMOTION_PROMOTION, req, null);
        return new Result();
    }

    @PatchMapping()
    @ApiOperation(value = "update Promotion", notes = "update Promotion.")
    public Object update(@RequestBody PromotionDto.Request req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_SALES_PROMOTION_PROMOTION, req, null);
        return new Result();
    }

    @PatchMapping("/status")
    @ApiOperation(value = "update status Promotion", notes = "update status Promotion.")
    public Object updateStatus(@RequestBody PromotionDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_SALES_PROMOTION_PROMOTION + "/status", req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    @ApiOperation(value = "delete Promotion", notes = "delete Promotion.")
    public Object delete(@RequestBody PromotionDto.DeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_SALES_PROMOTION_PROMOTION, req, null);
        return new Result();
    }
    @GetMapping("active")
    @ApiOperation(value = "getPromotion", notes = "get all Promotion.")
    public Object getPromotionActive(
            @Valid @ModelAttribute PromotionDto.ListReq req) {
        Object objectMap = service.getPromotionActive(req);
        logger.logApi(RequestMethod.GET, Constant.API_SALES_PROMOTION_PROMOTION +"active", req, objectMap);
        return objectMap;
    }
}
