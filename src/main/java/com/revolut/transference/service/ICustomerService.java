package com.revolut.transference.service;

import com.revolut.transference.domain.Customer;

/**
 * This class is intended to handle all customer operations like create, find, or any other business operation.
 */
public interface ICustomerService {

    /**
     * Saves a customer.
     *
     * @param customer an instance of {@link Customer} to be saved.
     * @return an instance of the new created {@link Customer}
     */
    Customer save(Customer customer);

    /**
     * Finds a customer by his id.
     *
     * @param id id of customer to be found.
     * @return an instance of the found {@link Customer}
     */
    Customer findById(long id);
}
