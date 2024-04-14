package com.datn.watch.api.merchandise.product.controller;

import com.datn.watch.common.Constant;
import com.datn.watch.common.logging.APILogger;
import com.datn.watch.common.model.json.Result;
import com.datn.watch.common.model.json.ResultData;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.datn.watch.api.merchandise.product.model.dto.ProductDto;
import com.datn.watch.api.merchandise.product.service.ProductService;

/**
 * @author tobi
 */
@RestController
@RequestMapping(value = Constant.API_MERCHANDISE_PRODUCT)
public class ProductRestController {

    private static final APILogger logger = new APILogger(ProductRestController.class);

    private final ProductService service;


    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public Object getProduct(@Valid @ModelAttribute ProductDto.ListReq req) {
        Object objectMap = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_PRODUCT, req, objectMap);
        return objectMap;
    }

    @GetMapping(path = "/{id}")
    public ResultData<Object> getProduct(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_MERCHANDISE_PRODUCT + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    public Object addProduct(@RequestBody ProductDto.Request req) {
        service.save(req);
        logger.logApi(RequestMethod.POST, Constant.API_MERCHANDISE_PRODUCT, req, null);
        return new Result();
    }

    @PatchMapping()
    public Object updateProduct(@RequestBody ProductDto.Request req) {
        service.update(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_PRODUCT, req, null);
        return new Result();
    }

    @DeleteMapping(path = "")
    public Object delete(@RequestBody ProductDto.DeleteReq id) {
        service.delete(id);
        logger.logApi(RequestMethod.DELETE, Constant.API_MERCHANDISE_PRODUCT, id, null);
        return new Result();
    }
    @PatchMapping("/status")
    public Object updateStatus(@RequestBody ProductDto.StatusReq req) {
        service.updateStatus(req);
        logger.logApi(RequestMethod.PATCH, Constant.API_MERCHANDISE_PRODUCT + "/status", req, null);
        return new Result();
    }
}
