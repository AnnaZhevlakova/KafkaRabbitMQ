package com.example.CreditService.entity;

import com.example.CreditService.enums.CreditOrderStatus;
import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name = "credit_applications", schema = "netology")
public class CreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "term_months", nullable = false)
    private Integer term;

    @Column(name = "income", nullable = false)
    private BigDecimal userIncome;

    @Column(name = "current_debt", nullable = false)
    private BigDecimal currentCreditLoad;

    @Column(name = "credit_score", nullable = false)
    private Integer creditRating;

    @Column(name = "status", nullable = false)
    private CreditOrderStatus status;

    public CreditEntity() {
    }

    public CreditEntity(long id, BigDecimal amount, Integer term, BigDecimal userIncome, BigDecimal currentCreditLoad, Integer creditRating, CreditOrderStatus status) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.userIncome = userIncome;
        this.currentCreditLoad = currentCreditLoad;
        this.creditRating = creditRating;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
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

    public BigDecimal getCurrentCreditLoad() {
        return currentCreditLoad;
    }

    public void setCurrentCreditLoad(BigDecimal currentCreditLoad) {
        this.currentCreditLoad = currentCreditLoad;
    }

    public Integer GetCreditRating() {
        return creditRating;
    }

    public void setCreditRating(Integer creditRating) {
        this.creditRating = creditRating;
    }

    public CreditOrderStatus getStatus() {
        return status;
    }

    public void setStatus(CreditOrderStatus status) {
        this.status = status;
    }
}
