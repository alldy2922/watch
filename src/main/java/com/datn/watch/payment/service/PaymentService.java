package com.datn.watch.payment.service;

import com.lib.payos.PayOS;
import com.lib.payos.type.ItemData;
import com.lib.payos.type.PaymentData;
import com.pet.market.api.common.exception.PaymentException;
import com.pet.market.api.order.model.dto.OrderDTO;
import com.pet.market.api.order.service.OrderService;
import com.pet.market.api.payment.model.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    private final PayOS payOS;
    private final OrderService orderService;

    public PaymentService(PayOS payOS, OrderService orderService) {
        super();
        this.payOS = payOS;
        this.orderService = orderService;
    }

    public Object createPaymentLink(PaymentDto.Request req) {
        OrderDTO.OrderRes orderRes = orderService.get(req.getOrderId());
        try {
            List<ItemData> itemList = new ArrayList<>();
            orderRes.getItems().forEach(orderItem -> {
                ItemData item = new ItemData(orderItem.getName(), orderItem.getQuantity(), Math.toIntExact(orderItem.getPrice()));
                itemList.add(item);
            });
            PaymentData paymentData = new PaymentData(generateRqCode(), Math.toIntExact(orderRes.getPrice()), "",
                    itemList, req.getCancelUrl(), req.getReturnUrl());
            return payOS.createPaymentLink(paymentData);
        } catch (Exception e) {
            throw new PaymentException(PaymentException.CREATE_QR_PAYMENT_IS_ERROR);
        }
    }

    public Object deletePaymentLink(int orderCode) {
        try {
            return payOS.cancelPaymentLink(orderCode, null);
        } catch (Exception e) {
            throw new PaymentException(PaymentException.CANCEL_QR_PAYMENT_IS_ERROR);
        }
    }
    private int generateRqCode() {
        //Gen order code
        String currentTimeString = String.valueOf(new Date().getTime());
        return Integer.parseInt(currentTimeString.substring(currentTimeString.length() - 6));
    }
}
