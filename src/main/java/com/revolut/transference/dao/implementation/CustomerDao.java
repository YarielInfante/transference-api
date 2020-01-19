package com.revolut.transference.dao.implementation;

import com.revolut.transference.dao.ICustomerDao;
import com.revolut.transference.domain.Account;
import com.revolut.transference.domain.Customer;
import com.revolut.transference.util.QueryLoader;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;

import static com.revolut.transference.web.mapper.AccountMapper.resultSetToAccount;
import static com.revolut.transference.web.mapper.CustomerMapper.resultSetToCustomer;

public class CustomerDao implements ICustomerDao {

    private Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Customer save(Customer customer) {

        Customer customerSaved = new Customer();

        try {
            BeanUtils.copyProperties(customerSaved, customer);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try (var statement = connection.prepareStatement(QueryLoader.getInsertCustomer(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getLastName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customerSaved.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("could not retrieve generated id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerSaved;
    }

    @Override
    public Customer findById(long id) {
        var customer = new Customer();
        var accounts = new HashSet<Account>();

        try (var statement = connection.prepareStatement(QueryLoader.getSelectCustomerFindOne())) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                customer = Optional.of(resultSet)
                        .map(resultSetToCustomer)
                        .get();
                customer.setAccounts(accounts);

                accounts.add(
                        Optional.of(resultSet)
                                .map(resultSetToAccount)
                                .get()
                );

                while (resultSet.next()) {
                    accounts.add(
                            Optional.of(resultSet)
                                    .map(resultSetToAccount)
                                    .get()
                    );
                }
            }

            return customer;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
