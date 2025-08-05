package com.example.demo.integration.vola;

import lombok.Data;
import java.time.Instant;

@Data
public class VolaPaymentResponse {
    private String id;
    private PspPayment pspPayment;
    private Instant creationInstant;
    private Instant lastPspVerificationInstant;
    private Integer verificationAttemptNb;
    private VerificationStatus verificationStatus;
}