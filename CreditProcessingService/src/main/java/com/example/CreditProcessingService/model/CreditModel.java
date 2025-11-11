package com.example.CreditProcessingService.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditModel {
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal userIncome;
    private BigDecimal currentCreditLoad;
    private String status;
}


