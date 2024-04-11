package com.datn.watch.request.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.request.enumeration.Status;
import com.pet.market.api.request.model.dto.RequestDto;
import com.pet.market.api.request.service.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api(" API v1  Request")
@Tag(name = "Request API v1")
@RequestMapping(value = Constant.API_REQUEST)
public class RequestRestController {

    private static final APILogger logger = new APILogger(RequestRestController.class);

    private final RequestService service;

    public RequestRestController(RequestService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "getRequest", notes = "get all Request.")
    public Object gets(@Valid @ModelAttribute RequestDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_REQUEST, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getRequest", notes = "get one Request.")
    public ResultData<Object> get(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_REQUEST + "/" + id, object);
        return resultData;
    }

//    @PostMapping()
//    @ApiOperation(value = "add Request", notes = "add Request.")
//    public Object add(@RequestBody RequestDto.Request req) {
//        service.save(req);
//        logger.logApi(RequestMethod.POST, Constant.API_REQUEST, req, null);
//        return new Result();
//    }
//
//    @PatchMapping()
//    @ApiOperation(value = "update Request", notes = "update Request.")
//    public Object update(@RequestBody RequestDto.Request req) {
//        service.update(req);
//        logger.logApi(RequestMethod.PATCH, Constant.API_REQUEST, req, null);
//        return new Result();
//    }
//
//    @DeleteMapping(path = "")
//    @ApiOperation(value = "delete Request", notes = "delete Request.")
//    public Object delete(@RequestBody RequestDto.ListIdReq req) {
//        service.deletes(req);
//        logger.logApi(RequestMethod.DELETE, Constant.API_REQUEST, req, null);
//        return new Result();
//    }

    @PatchMapping("/status/approval")
    @ApiOperation(value = "update status approval Request", notes = "update status approval Request.")
    public Object updateStatusApproval(@RequestBody RequestDto.ListIdReq req) {
        service.updateStatus(req, Status.APPROVED);
        logger.logApi(RequestMethod.PATCH, Constant.API_REQUEST + "/status/approval", req, null);
        return new Result();
    }

    @PatchMapping("/status/reject")
    @ApiOperation(value = "update status reject Request", notes = "update status reject Request.")
    public Object updateStatusReject(@RequestBody RequestDto.ListIdReq req) {
        service.updateStatus(req, Status.REJECTED);
        logger.logApi(RequestMethod.PATCH, Constant.API_REQUEST + "/status/reject", req, null);
        return new Result();
    }
}
