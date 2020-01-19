package com.revolut.transference.service;

import com.revolut.transference.web.dto.TopUpDto;
import com.revolut.transference.web.rest.exception.WrongAccountException;

/**
 * This class is intended to handle Top up operations.
 */
public interface ITopUpService {

    /**
     * Top up to an account
     *
     * @param topUpDto
     * @throws WrongAccountException
     */
    void topup(TopUpDto topUpDto) throws WrongAccountException;
}
