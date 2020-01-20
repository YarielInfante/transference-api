package com.revolut.transference.dao;

import com.revolut.transference.domain.Customer;

/**
 * Data access object for Customer entity. It handles database operations like save, find, etc.
 */
public interface ICustomerDao {

    /**
     * Saves a Customer
     *
     * @param customer an instance of {@link Customer} to be saved
     * @return an instace of the created {@link Customer}
     */
    Customer save(Customer customer);

    /**
     * Finds a Customer by the given id
     *
     * @param id id of the {@link Customer} to find
     * @return an instance of the found {@link Customer}
     */
    Customer findById(long id);
}
