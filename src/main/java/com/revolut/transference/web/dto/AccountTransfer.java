package com.revolut.transference.web.dto;

public class AccountTransfer {

    private long id;
    private String number;

    public AccountTransfer() {
    }

    public AccountTransfer(long id, String number) {
        this.id = id;
        this.number = number;
    }

    public AccountTransfer(long id) {
        this.id = id;
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
}
