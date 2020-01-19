package com.revolut.transference.dao.implementation;

import com.revolut.transference.dao.IAccountDao;
import com.revolut.transference.domain.Account;
import com.revolut.transference.util.QueryLoader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao implements IAccountDao {

    private Connection connection;

    public AccountDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account save(Account account) {

        try (var statement = connection.prepareStatement(QueryLoader.getInsertAccount(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, account.getNumber());
            statement.setLong(2, account.getCustomerId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("could not retrieve generated id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
