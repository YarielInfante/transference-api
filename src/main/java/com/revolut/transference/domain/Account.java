package com.revolut.transference.domain;

/**
 * Account entity
 */
public class Account {

    private long id;
    private String number;
    private long customerId;

    public Account() {
    }

    public Account(String number, long customerId) {
        this.number = number;
        this.customerId = customerId;
    }

    public Account(long id, String number, long customerId) {
        this.id = id;
        this.number = number;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", customerId=" + customerId +
                '}';
    }
}
