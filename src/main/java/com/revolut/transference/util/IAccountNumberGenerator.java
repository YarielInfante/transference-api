package com.revolut.transference.util;

/**
 * It generates the account number.
 */
public interface IAccountNumberGenerator {

    /**
     * Generates a number of 9 digits
     *
     * @return acccount number
     */
    String generateNumber();
}
