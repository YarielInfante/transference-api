package com.revolut.transference.dao.implementation;

import com.revolut.transference.dao.IJournalDao;
import com.revolut.transference.domain.Journal;
import com.revolut.transference.util.QueryLoader;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class JournalDao implements IJournalDao {

    private Connection connection;

    public JournalDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Journal save(Journal journal) {

        var journalSaved = new Journal();

        try {
            BeanUtils.copyProperties(journalSaved, journal);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try (var statement = connection.prepareStatement(QueryLoader.getInsertJournal(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, journal.getTransactionTypeId());
            statement.setDate(2, new Date(journal.getDate().getTime()));

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    journalSaved.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("could not retrieve generated id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journalSaved;
    }
}
