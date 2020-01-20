package com.revolut.transference.util;

/**
 * Handy Cash book account util to get information.
 */
public final class CashBookAccountUtil {

    private long id = 1;
    private String accountNumber = "000000000";
    private static CashBookAccountUtil INSTANCE;


    private CashBookAccountUtil() {
    }

    public static CashBookAccountUtil getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new CashBookAccountUtil();
        }

        return INSTANCE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
