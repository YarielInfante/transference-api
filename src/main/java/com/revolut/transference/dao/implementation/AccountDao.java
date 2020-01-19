package com.revolut.transference.dao.implementation;

import com.revolut.transference.dao.IAccountDao;
import com.revolut.transference.domain.Account;
import com.revolut.transference.util.QueryLoader;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
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

        var accountSaved = new Account();

        try {
            BeanUtils.copyProperties(accountSaved, account);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try (var statement = connection.prepareStatement(QueryLoader.getInsertAccount(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, account.getNumber());
            statement.setLong(2, account.getCustomerId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    accountSaved.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("could not retrieve generated id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountSaved;
    }

    public boolean accountExists(long customerId, long accountId) {

        try (var statement = connection.prepareStatement(QueryLoader.getSelectAccountFindByCustomerIdAndAccountNumber())) {
            statement.setLong(1, customerId);
            statement.setLong(2, accountId);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
