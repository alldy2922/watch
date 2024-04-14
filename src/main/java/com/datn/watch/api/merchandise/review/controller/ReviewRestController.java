package com.datn.watch.api.merchandise.review.controller;

import com.datn.watch.common.Constant;
import com.datn.watch.common.logging.APILogger;
import com.datn.watch.common.model.json.Result;
import com.datn.watch.common.model.json.ResultData;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.datn.watch.api.merchandise.review.model.dto.ReviewDto;
import com.datn.watch.api.merchandise.review.service.ReviewService;

/**
 * @author tobi
 */
@RestController
@RequestMapping(value = Constant.API_MERCHANDISE_REVIEW)
public class ReviewRestController {

    private static final APILogger logger = new APILogger(ReviewRestController.class);

    private final ReviewService service;


    public ReviewRestController(ReviewService service) {
        this.service = service;
    }

    @GetMapping()
    public Object gets(@Valid @ModelAttribute ReviewDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_REVIEW, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    public ResultData<Object> get(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_REVIEW + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    public Object add(@RequestBody ReviewDto.Request req) {
        try {
            service.save(req);
            logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_REVIEW, req, null);
        } catch (Exception ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PatchMapping()
    public Object update(@RequestBody ReviewDto.Request req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_REVIEW, req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    public Object delete(@RequestBody ReviewDto.DeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_REVIEW, req, null);
        return new Result();
    }

    @PatchMapping("/status")
    public Object updateStatus(@RequestBody ReviewDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_REVIEW + "/status", req, null);
        return new Result();
    }
}
