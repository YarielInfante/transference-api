package com.revolut.transference.service.implentation;

import com.revolut.transference.config.database.DataSource;
import com.revolut.transference.dao.implementation.AccountDao;
import com.revolut.transference.domain.Transaction;
import com.revolut.transference.service.ITransferService;
import com.revolut.transference.util.TransactionTypeUtil;
import com.revolut.transference.web.dto.MakeTransfer;
import com.revolut.transference.web.rest.exception.InsufficientBalanceException;
import com.revolut.transference.web.rest.exception.WrongAccountException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransferService implements ITransferService {

    @Override
    public void makeTransfer(MakeTransfer transfer) throws WrongAccountException, InsufficientBalanceException {

        Connection connection = null;

        try {
            connection = DataSource.getConnection();

            AccountDao accountDao = new AccountDao(connection);
            JournalService journalService = new JournalService(connection);

            boolean accountExists = accountDao.accountExists(transfer.getCustomerId(), transfer.getAccountOrigin().getId());

            if (!accountExists) {
                throw new WrongAccountException();
            }

            var accountDestination = accountDao.findByNumber(transfer.getAccountDestination().getNumber());

            if (accountDestination == null) {
                throw new WrongAccountException();
            }

            var balance = accountDao.getBalance(transfer.getAccountOrigin().getId());

            if (transfer.getAmount().compareTo(balance) > 0) {
                throw new InsufficientBalanceException();
            }

            journalService.registerTransaction(
                    TransactionTypeUtil.TRANSFER,
                    Transaction.build()
                            .withAccountId(transfer.getAccountOrigin().getId())
                            .withAmount(transfer.getAmount().negate()),
                    Transaction.build()
                            .withAccountId(accountDestination.getId())
                            .withAmount(transfer.getAmount())
            );


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
