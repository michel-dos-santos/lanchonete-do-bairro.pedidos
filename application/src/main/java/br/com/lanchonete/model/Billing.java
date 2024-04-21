package br.com.lanchonete.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Billing {

    private UUID id;
    private BigDecimal totalPrice;
    private StatusPaymentType status;
    private BillingFormType billingFormType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StatusPaymentType getStatus() {
        return status;
    }

    public void setStatus(StatusPaymentType status) {
        this.status = status;
    }

    public BillingFormType getBillingFormType() {
        return billingFormType;
    }

    public void setBillingFormType(BillingFormType billingFormType) {
        this.billingFormType = billingFormType;
    }

}
