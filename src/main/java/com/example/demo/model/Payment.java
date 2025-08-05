package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payment {
    private Integer id;
    private LocalDateTime payementDate;
    private Double amount;
    private PaymentMethodes paymentMethodes;
}
