package com.revolut.transference.web.rest.exception;

import com.revolut.transference.web.dto.ErrorCode;
import com.revolut.transference.web.dto.HttpResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InsufficientBalanceException extends Exception implements ExceptionMapper<InsufficientBalanceException> {

    private static final Logger logger = LoggerFactory.getLogger(InsufficientBalanceException.class);

    @Override
    public Response toResponse(InsufficientBalanceException e) {

        logger.error(e.getMessage(), e);

        return Response.status(400)
                .entity(new HttpResponseError(ErrorCode.INSUFFICIENT_BALANCE, "Balance is insufficient"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();

    }
}
