package com.revolut.transference.service;

import com.revolut.transference.domain.Transaction;
import com.revolut.transference.util.TransactionTypeUtil;

/**
 * This class is intended to handle all Journal operations like create, find, or any other business operation.
 */
public interface IJournalService {

    /**
     * Register a transaction into Journal and Transaction.
     *
     * @param transactionType Type of transction to register
     * @param transaction     an instance of the transaction to make
     * @see TransactionTypeUtil
     */
    void registerTransaction(TransactionTypeUtil transactionType, Transaction transaction);
}
