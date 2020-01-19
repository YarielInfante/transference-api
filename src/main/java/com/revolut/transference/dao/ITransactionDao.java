package com.revolut.transference.dao;

import com.revolut.transference.domain.Transaction;

/**
 * Data access object for Account entity. It handles database operations like save, find, etc.
 */
public interface ITransactionDao {

    /**
     * Makes a transaction
     *
     * @param transaction an instance of {@link Transaction}
     * @return an instance of the new {@link Transaction}
     */
    Transaction makeTransaction(Transaction transaction);
}
