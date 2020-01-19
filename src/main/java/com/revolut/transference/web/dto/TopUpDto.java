package com.revolut.transference.web.dto;

import java.math.BigDecimal;

public class TopUpDto {

    private long customerId;
    private AccountTopup account;
    private BigDecimal amount;

    public AccountTopup getAccount() {
        return account;
    }

    public void setAccount(AccountTopup account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
