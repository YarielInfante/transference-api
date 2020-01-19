package com.revolut.transference.mapper;

import com.revolut.transference.domain.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class AccountMapper {

    public static Function<ResultSet, Account> resultSetToAccount = resultSet -> {
        try {
            return new Account(
                    resultSet.getLong("ACCOUNT_ID"),
                    resultSet.getString("ACCOUNT_NUMBER"),
                    resultSet.getLong("ACCOUNT_CUSTOMER_ID")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };
}
