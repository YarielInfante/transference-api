package com.revolut.transference.web.dto;

public class AccountTopup {

    private long id;
    private String number;

    public AccountTopup() {
    }

    public AccountTopup(long id) {
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
