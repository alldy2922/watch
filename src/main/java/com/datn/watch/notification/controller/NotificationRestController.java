package com.datn.watch.notification.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.notification.model.dto.NotificationDto;
import com.pet.market.api.notification.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api(" API v1  Notification")
@Tag(name = "Notification API v1")
@RequestMapping(value = Constant.API_NOTIFICATION)
public class NotificationRestController {

    private static final APILogger logger = new APILogger(NotificationRestController.class);

    private final NotificationService service;

    public NotificationRestController(NotificationService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "get my Notification", notes = "get all my Notification.")
    public Object gets(@Valid @ModelAttribute NotificationDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_NOTIFICATION, req, objectMap);
        return objectMap;
    }

    @GetMapping("/count-unread")
    @ApiOperation(value = "get the number of my unread notifications", notes = "get the number of my unread notifications.")
    public Object getMyUnreadNotificationCount(@Valid @ModelAttribute NotificationDto.ListReq req) {
        ResultData<Object> resultData = new ResultData<>();
        resultData.setResultData(service.getMyUnreadNotificationCount(req));
        logger.logApi(RequestMethod.GET, Constant.API_NOTIFICATION + "/count-unread", req, resultData);
        return resultData;
    }

    @PatchMapping(path = "/read")
    @ApiOperation(value = "Mark My Notifications as Read", notes = "Mark My Notifications as Read.")
    public Object markMyNotificationRead(@RequestBody NotificationDto.ListIdReq req) {
        service.markMyNotificationRead(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_NOTIFICATION + "/read", req, null);
        return new Result();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "get Notification", notes = "get one  Notification.")
    public ResultData<Object> get(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_NOTIFICATION + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add  Notification", notes = "add  Notification.")
    public Object add(@RequestBody NotificationDto.Request req) {
        service.save(req);
        logger.logApi(RequestMethod.POST, Constant.API_NOTIFICATION, req, null);
        return new Result();
    }

//    @PatchMapping()
//    @ApiOperation(value = "update  Notification", notes = "update  Notification.")
//    public Object update(@RequestBody NotificationDto.Request req) {
//        service.update(req);
//        logger.logApi(RequestMethod.PATCH, Constant.API_NOTIFICATION, req, null);
//        return new Result();
//    }

//    @DeleteMapping(path = "")
//    @ApiOperation(value = "delete  Notification", notes = "delete  Notification.")
//    public Object delete(@RequestBody NotificationDto.ListIdReq req) {
//        service.deletes(req);
//        logger.logApi(RequestMethod.DELETE, Constant.API_NOTIFICATION, req, null);
//        return new Result();
//    }
}
