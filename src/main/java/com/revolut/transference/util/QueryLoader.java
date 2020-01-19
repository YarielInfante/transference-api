package com.revolut.transference.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueryLoader {

    private static String insertCustomer;
    private static String insertAccount;
    private static String selectCustomerFindOne;

    static {

        Properties prop = new Properties();

        try {

            InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream("sqlStatement.properties");

            prop.load(input);

            insertCustomer = prop.getProperty("insert.customer");
            insertAccount = prop.getProperty("insert.account");
            selectCustomerFindOne = prop.getProperty("select.customer.findOne");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static String getInsertCustomer() {
        return insertCustomer;
    }

    public static void setInsertCustomer(String insertCustomer) {
        QueryLoader.insertCustomer = insertCustomer;
    }

    public static String getInsertAccount() {
        return insertAccount;
    }

    public static void setInsertAccount(String insertAccount) {
        QueryLoader.insertAccount = insertAccount;
    }

    public static String getSelectCustomerFindOne() {
        return selectCustomerFindOne;
    }

    public static void setSelectCustomerFindOne(String selectCustomerFindOne) {
        QueryLoader.selectCustomerFindOne = selectCustomerFindOne;
    }
}
