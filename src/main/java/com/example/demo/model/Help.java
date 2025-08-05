package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Help {
    private Integer id;
    private Donor beneficiary;
    private Payment payment;
    private String accidentDescription;
}
