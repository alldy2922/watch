package com.datn.watch.payment.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PaymentDto {
    @Data
    @AllArgsConstructor
    public static class Request {
        @NotEmpty
        private Long orderId;
        @NotEmpty
        private String returnUrl;
        @NotEmpty
        private String cancelUrl;
    }
}
