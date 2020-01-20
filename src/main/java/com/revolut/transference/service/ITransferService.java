package com.revolut.transference.service;

import com.revolut.transference.web.dto.MakeTransfer;
import com.revolut.transference.web.rest.exception.InsufficientBalanceException;
import com.revolut.transference.web.rest.exception.WrongAccountException;

public interface ITransferService {

    void makeTransfer(MakeTransfer transfer) throws WrongAccountException, InsufficientBalanceException;
}
