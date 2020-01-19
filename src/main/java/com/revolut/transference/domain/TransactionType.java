package com.revolut.transference.domain;

/**
 * Transaction type entity
 */
public class TransactionType {

    private long id;
    private String name;

    public TransactionType() {
    }

    public TransactionType(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
