package com.datn.watch.api.merchandise.brand.controller;

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

import com.datn.watch.api.merchandise.brand.model.dto.BrandDto;
import com.datn.watch.api.merchandise.brand.service.BrandService;
import com.datn.watch.common.Constant;
import com.datn.watch.common.logging.APILogger;
import com.datn.watch.common.model.json.Result;
import com.datn.watch.common.model.json.ResultData;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = Constant.API_MERCHANDISE_BRAND)
public class BranchRestController {

    private static final APILogger logger = new APILogger(BranchRestController.class);

    private final BrandService service;


    public BranchRestController(BrandService service) {
        this.service = service;
    }

    @GetMapping()
    public Object gets(
            @Valid @ModelAttribute BrandDto.BrandListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_BRAND, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    public ResultData<Object> get(@PathVariable Long id) throws Exception {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_BRAND + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    public Object add(@ModelAttribute BrandDto.BrandReq req) {
        try {
            service.save(req);
            logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_BRAND, req, null);
        } catch (Exception ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PatchMapping()
    public Object update(@ModelAttribute BrandDto.BrandReq req) {
        try {
            service.update(req);
            logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_BRAND, req, null);
        } catch (Exception ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }
    @PatchMapping("/status")
    public Object updateStatus(@RequestBody BrandDto.BrandStatusReq req) {
            service.updateStatus(req);
            logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_BRAND + "/status", req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    public Object delete(@RequestBody BrandDto.BrandDeleteReq req) {
        service.deletes(req);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_BRAND , req, null);
        return new Result();
    }
}
