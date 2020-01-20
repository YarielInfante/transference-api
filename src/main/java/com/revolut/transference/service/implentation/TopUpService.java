package com.revolut.transference.service.implentation;

import com.revolut.transference.config.database.DataSource;
import com.revolut.transference.dao.implementation.AccountDao;
import com.revolut.transference.domain.Transaction;
import com.revolut.transference.service.ITopUpService;
import com.revolut.transference.util.CashBookAccountUtil;
import com.revolut.transference.util.TransactionTypeUtil;
import com.revolut.transference.web.dto.TopUpDto;
import com.revolut.transference.web.rest.exception.WrongAccountException;

import java.sql.Connection;
import java.sql.SQLException;

public class TopUpService implements ITopUpService {


    @Override
    public void topup(TopUpDto topUpDto) throws WrongAccountException {

        Connection connection = null;

        try {
            connection = DataSource.getConnection();

            connection.setAutoCommit(false);

            var accountDao = new AccountDao(connection);
            var journalService = new JournalService(connection);

            boolean accountExists = accountDao.accountExists(topUpDto.getCustomerId(), topUpDto.getAccount().getId());

            if (!accountExists) {
                throw new WrongAccountException();
            }

            var cashbook = CashBookAccountUtil.getInstance();

            journalService.registerTransaction(
                    TransactionTypeUtil.DEPOSIT,
                    Transaction.build()
                            .withAccountId(cashbook.getId())
                            .withAmount(topUpDto.getAmount().negate()),
                    Transaction.build()
                            .withAccountId(topUpDto.getAccount().getId())
                            .withAmount(topUpDto.getAmount())
            );


            connection.commit();

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
