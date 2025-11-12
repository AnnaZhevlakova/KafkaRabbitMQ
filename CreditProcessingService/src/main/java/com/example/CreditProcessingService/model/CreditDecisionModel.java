package com.example.CreditProcessingService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDecisionModel {
    private Long applicationId;
    private boolean approved;
    private BigDecimal approvedAmount;
    private String message;
}
