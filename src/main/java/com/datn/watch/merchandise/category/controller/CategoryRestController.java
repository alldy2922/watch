package com.datn.watch.merchandise.category.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesCreated;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.anotation.LogMethod;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.merchandise.category.model.dto.CategoryDto;
import com.pet.market.api.merchandise.category.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api(" API v1  Category")
@Tag(name = "Category API v1")
@RequestMapping(value = Constant.API_MERCHANDISE_CATEGORY)
public class CategoryRestController {

    private static final APILogger logger = new APILogger(CategoryRestController.class);

    private final CategoryService service;


    public CategoryRestController(CategoryService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "getCategory", notes = "get all Category.")
    @ApiResponsesOk()
    @LogMethod()
    public Object getCategory(
            @Valid @ModelAttribute CategoryDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_CATEGORY, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "getCategory", notes = "get one Category.")
    @ApiResponsesOk()
    public ResultData<Object> getCategory(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_CATEGORY + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    @ApiOperation(value = "add Category", notes = "add Category.")
    @ApiResponsesCreated()
    @LogMethod()
    public Object addCategory(@Valid @RequestBody CategoryDto.CategoryReq req) {
        service.save(req);
        logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_CATEGORY, req, null);
        return new Result();
    }

    @PatchMapping()
    @ApiOperation(value = "update Category", notes = "update Category.")
    @ApiResponsesCreated()
    @LogMethod()
    public Object updateCategory(@Valid @RequestBody CategoryDto.CategoryReq req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_CATEGORY, req, null);
        return new Result();
    }

    @PatchMapping("/status")
    @ApiOperation(value = "update status Category", notes = "update status Category.")
    @ApiResponsesCreated()
    public Object updateStatus(@RequestBody CategoryDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_CATEGORY + "/status", req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    @ApiOperation(value = "delete Category", notes = "delete Category.")
    public Object deleteCategory(@RequestBody CategoryDto.DeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_CATEGORY, req, null);
        return new Result();
    }

    @GetMapping(path = "/attributes")
    @ApiOperation(value = "get Category Attributes.", notes = "get Category Attributes.")
    public Object getAttributes(@ModelAttribute CategoryDto.IdsReq req) {
        Object obj = service.getAttributes(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_CATEGORY + "/attributes", req, obj);
        return obj;
    }
}
