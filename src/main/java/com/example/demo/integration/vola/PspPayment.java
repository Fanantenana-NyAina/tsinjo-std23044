package com.example.demo.integration.vola;

import java.time.Instant;
import lombok.Data;

@Data
public class PspPayment {
  private VolaPaymentResponse pspType;
  private String id;
  private Integer amount;
  private Instant creationInstant;
}
