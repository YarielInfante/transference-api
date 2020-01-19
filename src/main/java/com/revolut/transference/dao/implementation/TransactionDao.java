package com.revolut.transference.dao.implementation;

import com.revolut.transference.dao.ITransactionDao;
import com.revolut.transference.domain.Transaction;
import com.revolut.transference.util.QueryLoader;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class TransactionDao implements ITransactionDao {

    private Connection connection;

    public TransactionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Transaction makeTransaction(Transaction transaction) {

        var transactionSaved = new Transaction();

        try {
            BeanUtils.copyProperties(transactionSaved, transaction);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        try (var statement = connection.prepareStatement(QueryLoader.getInsertTransaction(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, transaction.getAccountId());
            statement.setLong(2, transaction.getJournalId());
            statement.setBigDecimal(3, transaction.getAmount());
            statement.setDate(4, new Date(transaction.getDate().getTime()));
            statement.setString(5,transaction.getReference());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transactionSaved.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("could not retrieve generated id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionSaved;
    }

}
