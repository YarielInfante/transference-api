package com.revolut.transference.service.implentation;

import com.revolut.transference.dao.implementation.JournalDao;
import com.revolut.transference.dao.implementation.TransactionDao;
import com.revolut.transference.domain.Journal;
import com.revolut.transference.domain.Transaction;
import com.revolut.transference.service.IJournalService;
import com.revolut.transference.util.CashBookAccountUtil;
import com.revolut.transference.util.TransactionTypeUtil;

import java.sql.Connection;
import java.util.Date;

public class JournalService implements IJournalService {

    private Connection connection;

    public JournalService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerTransaction(TransactionTypeUtil transactionType, Transaction transaction) {


        var journalDao = new JournalDao(connection);
        var transactionDao = new TransactionDao(connection);

        switch (transactionType) {
            case DEPOSIT: {

                var cashbook = CashBookAccountUtil.getInstance();
                var date = new Date();

                var journalSaved = journalDao.save(
                        new Journal(
                                transactionType.getId(),
                                new Date())
                );

                transactionDao.makeTransaction(
                        Transaction.build()
                                .withAccountId(cashbook.getId())
                                .withJournalId(journalSaved.getId())
                                .withAmount(transaction.getAmount().negate())
                                .withDate(date)
                                .withReference(transaction.getReference())
                );


                transactionDao.makeTransaction(
                        Transaction.build()
                        .withAccountId(transaction.getAccountId())
                        .withJournalId(journalSaved.getId())
                        .withAmount(transaction.getAmount())
                        .withDate(date)
                        .withReference(transaction.getReference())
                );
            }
            case TRANSFER: {

            }
        }

    }
}
