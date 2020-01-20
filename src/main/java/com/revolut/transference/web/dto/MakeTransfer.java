package com.revolut.transference.web.dto;

import java.math.BigDecimal;

public class MakeTransfer {

    private long customerId;
    private AccountTransfer accountOrigin;
    private AccountTransfer accountDestination;
    private BigDecimal amount;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public AccountTransfer getAccountOrigin() {
        return accountOrigin;
    }

    public void setAccountOrigin(AccountTransfer accountOrigin) {
        this.accountOrigin = accountOrigin;
    }

    public AccountTransfer getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(AccountTransfer accountDestination) {
        this.accountDestination = accountDestination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
