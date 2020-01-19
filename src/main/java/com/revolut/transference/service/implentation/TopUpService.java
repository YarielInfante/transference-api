package com.revolut.transference.service.implentation;

import com.revolut.transference.annotation.Component;
import com.revolut.transference.config.database.DataSource;
import com.revolut.transference.dao.implementation.AccountDao;
import com.revolut.transference.domain.Transaction;
import com.revolut.transference.service.ITopUpService;
import com.revolut.transference.util.TransactionTypeUtil;
import com.revolut.transference.web.dto.TopUpDto;
import com.revolut.transference.web.rest.exception.WrongAccountException;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TopUpService implements ITopUpService {


    @Override
    public void topup(TopUpDto topUpDto) throws WrongAccountException {

        Connection connection = null;

        try {
            connection = DataSource.getConnection();

            var accountDao = new AccountDao(connection);
            var journalService = new JournalService(connection);

            boolean accountExists = accountDao.accountExists(topUpDto.getCustomerId(), topUpDto.getAccount().getId());

            if (!accountExists) {
                throw new WrongAccountException();
            }

            journalService.registerTransaction(
                    TransactionTypeUtil.DEPOSIT,
                    Transaction.build()
                            .withAccountId(topUpDto.getAccount().getId())
                            .withJournalId(0)
                            .withAmount(topUpDto.getAmount())
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
