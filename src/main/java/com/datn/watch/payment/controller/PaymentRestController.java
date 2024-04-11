package com.datn.watch.payment.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.payment.model.PaymentDto;
import com.pet.market.api.payment.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobi
 */
@RestController
@Api(" API v1  Payment")
@Tag(name = "Payment API v1")
@RequestMapping(Constant.API_PAYMENT)
public class PaymentRestController {
    private static final APILogger logger = new APILogger(PaymentRestController.class);
    private final PaymentService service;

    public PaymentRestController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("order")
    public Object createPaymentLink(@RequestBody PaymentDto.Request req) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.createPaymentLink(req);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.POST, Constant.API_PAYMENT + "/order", req, object);
        return resultData;
    }

    @DeleteMapping("order/{orderCode}")
    public Object deletePaymentLink(@PathVariable("orderCode") int orderCode) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.deletePaymentLink(orderCode);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.DELETE, Constant.API_PAYMENT + "/order" + orderCode, null, object);
        return resultData;
    }
}
