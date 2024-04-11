package com.datn.watch.merchandise.review.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesCreated;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.merchandise.review.model.dto.ReviewDto;
import com.pet.market.api.merchandise.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api(" API v1  Review")
@Tag(name = "Review API v1")
@RequestMapping(value = Constant.API_MERCHANDISE_REVIEW)
public class ReviewRestController {

    private static final APILogger logger = new APILogger(ReviewRestController.class);

    private final ReviewService service;


    public ReviewRestController(ReviewService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "getReview", notes = "get all Review.")
    @ApiResponsesOk()
    public Object gets(@Valid @ModelAttribute ReviewDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_REVIEW, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getReview", notes = "get one Review.")
    @ApiResponsesOk()
    public ResultData<Object> get(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_REVIEW + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add Review", notes = "add Review.")
    @ApiResponsesCreated()
    public Object add(@RequestBody ReviewDto.Request req) {
        try {
            service.save(req);
            logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_REVIEW, req, null);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PatchMapping()
    @ApiOperation(value = "update Review", notes = "update Review.")
    @ApiResponsesCreated()
    public Object update(@RequestBody ReviewDto.Request req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_REVIEW, req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    @ApiOperation(value = "delete Review", notes = "delete Review.")
    public Object delete(@RequestBody ReviewDto.DeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_REVIEW, req, null);
        return new Result();
    }

    @PatchMapping("/status")
    @ApiOperation(value = "update status Review", notes = "update status Review.")
    @ApiResponsesCreated()
    public Object updateStatus(@RequestBody ReviewDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_REVIEW + "/status", req, null);
        return new Result();
    }
}
