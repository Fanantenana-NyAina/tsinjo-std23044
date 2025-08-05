package com.example.demo.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payment {
  private Integer id;
  private LocalDateTime payementDate;
  private Double amount;
  private PaymentMethodes paymentMethodes;
  private PaymentStatus paymentStatus;
}
