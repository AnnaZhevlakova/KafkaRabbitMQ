package com.example.CreditService.model;

import java.math.BigDecimal;

public class CreditRequest {
    private BigDecimal amount;
    private Integer term;
    private BigDecimal userIncome;
    private BigDecimal currentCreditLoad;
    private Integer creditRating;

    public CreditRequest(){}

    public CreditRequest(BigDecimal amount, Integer term, BigDecimal userIncome, BigDecimal currentCreditLoad, Integer creditRating){
            this.amount = amount;
            this.term = term;
            this.userIncome = userIncome;
            this.currentCreditLoad = currentCreditLoad;
            this.creditRating = creditRating;

        }

        public BigDecimal getAmount(){
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public Integer getTerm(){
            return term;
        }

        public void setTerm(Integer term) {
            this.term = term;
        }

        public BigDecimal getUserIncome() {
            return userIncome;
        }

        public void setUserIncome(BigDecimal userIncome) {
            this.userIncome = userIncome;
        }
        public BigDecimal getCurrentCreditLoad(){
            return currentCreditLoad;
        }
        public Integer GetCreditRating(){
            return creditRating;
        }

    public Integer getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(Integer creditRating) {
            this.creditRating = creditRating;
        }

    }
