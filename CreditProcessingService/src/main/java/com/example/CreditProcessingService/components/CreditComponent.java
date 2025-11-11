package com.example.CreditProcessingService.components;

import com.example.CreditProcessingService.model.CreditModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CreditComponent {
   // private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public CreditComponent(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @KafkaListener(topics = "CreditApplicationSubmitted",containerFactory = "kafkaConsumer")
    public void consume(CreditModel message) throws JsonProcessingException {
      //  CreditModel event = mapper.readValue(message, CreditModel.class);
      var appLoan = approveLoan(message.getAmount(),message.getTerm(),message.getUserIncome(),message.getCurrentCreditLoad());
     var t = message;

       /* BigDecimal monthlyIncome = event.income();
        BigDecimal monthlyDebt = event.currentDebt();
        BigDecimal monthlyPayment = calculateMonthlyPayment(event.amount(), event.termMonths());

        boolean approved = (monthlyPayment.add(monthlyDebt)).compareTo(monthlyIncome.multiply(new BigDecimal("0.5"))) <= 0;

        var decision = new CreditDecisionEvent(event.applicationId(), approved);
        String payload = mapper.writeValueAsString(decision);

        rabbitTemplate.convertAndSend("credit.decisions", payload);*/
    }

    public static boolean approveLoan(
            BigDecimal loanAmount,
            int loanTermMonths,
            BigDecimal monthlyIncome,
            BigDecimal currentLoanPayments)
            {


        if (monthlyIncome == null || monthlyIncome.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (loanAmount == null || loanAmount.compareTo(BigDecimal.ZERO) <= 0 || loanTermMonths <= 0) {
            return false;
        }
        if (currentLoanPayments == null) {
            currentLoanPayments = BigDecimal.ZERO;
        }


        BigDecimal annualRate = new BigDecimal("0.12"); // 12% годовых
        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);


        BigDecimal monthlyPayment;
        if (monthlyRate.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal power = BigDecimal.ONE.add(monthlyRate)
                    .pow(loanTermMonths, java.math.MathContext.DECIMAL128);
            BigDecimal numerator = monthlyRate.multiply(power);
            BigDecimal denominator = power.subtract(BigDecimal.ONE);
            monthlyPayment = loanAmount.multiply(numerator)
                    .divide(denominator, 2, RoundingMode.HALF_UP);
        } else {
            monthlyPayment = loanAmount.divide(BigDecimal.valueOf(loanTermMonths), 2, RoundingMode.HALF_UP);
        }


        BigDecimal totalMonthlyPayments = currentLoanPayments.add(monthlyPayment);
        BigDecimal maxAllowed = monthlyIncome.multiply(new BigDecimal("0.5"));


        return totalMonthlyPayments.compareTo(maxAllowed) <= 0;
    }

}
