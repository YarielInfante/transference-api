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
public class WrongAccountException extends Exception implements ExceptionMapper<WrongAccountException> {

    private static final Logger logger = LoggerFactory.getLogger(WrongAccountException.class);

    @Override
    public Response toResponse(WrongAccountException e) {

        logger.error(e.getMessage(), e);

        return Response.status(404)
                .entity(new HttpResponseError(ErrorCode.WRONG_ACCOUNT, "Account is incorrect"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
