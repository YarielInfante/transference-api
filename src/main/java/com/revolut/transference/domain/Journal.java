package com.revolut.transference.domain;

import java.util.Date;

public class Journal {

    private long id;
    private long transactionTypeId;
    private Date date;

    public Journal() {
    }

    public Journal(long transactionTypeId, Date date) {
        this.transactionTypeId = transactionTypeId;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
