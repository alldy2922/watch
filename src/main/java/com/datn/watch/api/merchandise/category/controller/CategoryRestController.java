package com.datn.watch.api.merchandise.category.controller;

import com.datn.watch.common.Constant;
import com.datn.watch.common.logging.APILogger;
import com.datn.watch.common.model.json.Result;
import com.datn.watch.common.model.json.ResultData;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.datn.watch.api.merchandise.category.model.dto.CategoryDto;
import com.datn.watch.api.merchandise.category.service.CategoryService;

/**
 * @author tobi
 */
@RestController
@RequestMapping(value = Constant.API_MERCHANDISE_CATEGORY)
public class CategoryRestController {

    private static final APILogger logger = new APILogger(CategoryRestController.class);

    private final CategoryService service;


    public CategoryRestController(CategoryService service) {
        this.service = service;
    }

    @GetMapping()
    public Object getCategory(
            @Valid @ModelAttribute CategoryDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_CATEGORY, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    public ResultData<Object> getCategory(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_CATEGORY + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    public Object addCategory(@Valid @RequestBody CategoryDto.CategoryReq req) {
        service.save(req);
        logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_CATEGORY, req, null);
        return new Result();
    }

    @PatchMapping()
    public Object updateCategory(@Valid @RequestBody CategoryDto.CategoryReq req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_CATEGORY, req, null);
        return new Result();
    }

    @PatchMapping("/status")
    public Object updateStatus(@RequestBody CategoryDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_CATEGORY + "/status", req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    public Object deleteCategory(@RequestBody CategoryDto.DeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_CATEGORY, req, null);
        return new Result();
    }

    @GetMapping(path = "/attributes")
    public Object getAttributes(@ModelAttribute CategoryDto.IdsReq req) {
        Object obj = service.getAttributes(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_CATEGORY + "/attributes", req, obj);
        return obj;
    }
}
