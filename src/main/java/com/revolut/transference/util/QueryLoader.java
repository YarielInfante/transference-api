package com.revolut.transference.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueryLoader {

    private static String insertCustomer;
    private static String insertAccount;
    private static String selectCustomerFindOne;
    private static String selectAccountFindByCustomerIdAndAccountNumber;
    private static String insertJournal;
    private static String insertTransaction;
    private static String selectAccountFindByNumber;
    private static String selectAccountBalance;

    static {

        Properties prop = new Properties();

        try {

            InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream("sqlStatement.properties");

            prop.load(input);

            insertCustomer = prop.getProperty("insert.customer");
            insertAccount = prop.getProperty("insert.account");
            selectCustomerFindOne = prop.getProperty("select.customer.findOne");
            selectAccountFindByCustomerIdAndAccountNumber = prop.getProperty("select.account.findByCustomerIdAndAccountNumber");
            insertJournal = prop.getProperty("insert.journal");
            insertTransaction = prop.getProperty("insert.transaction");
            selectAccountFindByNumber = prop.getProperty("select.account.findByNumber");
            selectAccountBalance = prop.getProperty("select.account.balance");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static String getInsertCustomer() {
        return insertCustomer;
    }

    public static String getInsertAccount() {
        return insertAccount;
    }

    public static String getSelectCustomerFindOne() {
        return selectCustomerFindOne;
    }

    public static String getSelectAccountFindByCustomerIdAndAccountNumber() {
        return selectAccountFindByCustomerIdAndAccountNumber;
    }

    public static String getInsertJournal() {
        return insertJournal;
    }

    public static String getInsertTransaction() {
        return insertTransaction;
    }

    public static String getSelectAccountFindByNumber() {
        return selectAccountFindByNumber;
    }

    public static String getSelectAccountBalance() {
        return selectAccountBalance;
    }
}
