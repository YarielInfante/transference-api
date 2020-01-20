package com.revolut.transference.service.implentation;

import com.revolut.transference.config.database.DataSource;
import com.revolut.transference.dao.IAccountDao;
import com.revolut.transference.dao.ICustomerDao;
import com.revolut.transference.dao.implementation.AccountDao;
import com.revolut.transference.dao.implementation.CustomerDao;
import com.revolut.transference.domain.Account;
import com.revolut.transference.domain.Customer;
import com.revolut.transference.service.ICustomerService;
import com.revolut.transference.util.IAccountNumberGenerator;
import com.revolut.transference.util.TOTPManager;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerService implements ICustomerService {


    @Override
    public Customer save(Customer customer) {
        Connection connection = null;
        Customer customerSaved = null;
        try {
            connection = DataSource.getConnection();

            connection.setAutoCommit(false);

            ICustomerDao customerDao = new CustomerDao(connection);
            IAccountDao accountDao = new AccountDao(connection);
            IAccountNumberGenerator numberGenerator = new TOTPManager();

            customerSaved = customerDao.save(customer);
            accountDao.save(new Account(numberGenerator.generateNumber(), customerSaved.getId()));

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return customerSaved;
    }

    @Override
    public Customer findById(long id) {
        Connection connection = null;
        try {

            connection = DataSource.getConnection();

            return new CustomerDao(connection)
                    .findById(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
