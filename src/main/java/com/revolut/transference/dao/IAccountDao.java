package com.revolut.transference.dao;

import com.revolut.transference.domain.Account;

/**
 * Data access object for Account entity. It handles database operations like save, find, etc.
 */
public interface IAccountDao {

    /**
     * Saves an Account
     *
     * @param account an instance of {@link Account} to be saved
     * @return an instance of the created {@link Account}
     */
    Account save(Account account);
}
