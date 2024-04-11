package com.datn.watch.payment.client;

import com.lib.payos.PayOS;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class VietQrClient {
    @Value("${app.payment.vietQr.payOsClientId}")
    private String clientId;

    @Value("${app.payment.vietQr.payOsApiKey}")
    private String apiKey;

    @Value("${app.payment.vietQr.payOsCheckSumKey}")
    private String checksumKey;

    @Bean
    public PayOS payOS() {
        return new PayOS(clientId, apiKey, checksumKey);
    }
}
