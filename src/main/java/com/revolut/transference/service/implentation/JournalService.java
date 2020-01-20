package com.revolut.transference.service.implentation;

import com.revolut.transference.dao.implementation.JournalDao;
import com.revolut.transference.dao.implementation.TransactionDao;
import com.revolut.transference.domain.Journal;
import com.revolut.transference.domain.Transaction;
import com.revolut.transference.service.IJournalService;
import com.revolut.transference.util.TransactionTypeUtil;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;

public class JournalService implements IJournalService {

    private Connection connection;

    public JournalService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerTransaction(TransactionTypeUtil transactionType, Transaction... transaction) {

        var journalDao = new JournalDao(connection);
        var transactionDao = new TransactionDao(connection);

        Date date = new Date();

        var journalSaved = journalDao.save(
                new Journal(
                        transactionType.getId(),
                        new Date())
        );

        Arrays.stream(transaction).forEach(t -> transactionDao.makeTransaction(
                Transaction.build()
                        .withAccountId(t.getAccountId())
                        .withJournalId(journalSaved.getId())
                        .withAmount(t.getAmount())
                        .withDate(date)
                        .withReference(t.getReference())
        ));

    }
}
