package com.revolut.transference.util;


/**
 * Handy util to get transaction type entity
 */
public enum TransactionTypeUtil {

    DEPOSIT(1, "Deposit"),
    TRANSFER(2, "Transfer");

    private final int id;
    private final String name;

    TransactionTypeUtil(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
