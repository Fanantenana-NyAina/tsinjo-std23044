package com.example.demo.integration.vola;

import lombok.Data;

import java.time.Instant;

@Data
public class PspPayment {
    private VolaPaymentResponse.PspType pspType;
    private String id;
    private Integer amount;
    private Instant creationInstant;
}
