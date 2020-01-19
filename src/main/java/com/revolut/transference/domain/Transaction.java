package com.revolut.transference.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private long id;
    private long accountId;
    private long journalId;
    private BigDecimal amount;
    private Date date;
    private String reference;

    public Transaction() {
    }


    public static Transaction build(){
        return new Transaction();
    }

    public Transaction withId(long id){
        setId(id);
        return this;
    }

    public Transaction withAccountId(long accountId){
        setAccountId(accountId);
        return this;
    }

    public Transaction withJournalId(long journalId){
        setJournalId(journalId);
        return this;
    }

    public Transaction withAmount(BigDecimal amount){
        setAmount(amount);
        return this;
    }

    public Transaction withDate(Date date){
        setDate(date);
        return this;
    }

    public Transaction withReference(String reference){
        setReference(reference);
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getJournalId() {
        return journalId;
    }

    public void setJournalId(long journalId) {
        this.journalId = journalId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
