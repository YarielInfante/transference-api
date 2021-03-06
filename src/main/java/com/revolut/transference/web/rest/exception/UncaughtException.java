package com.revolut.transference.web.rest.exception;

import com.revolut.transference.web.dto.ErrorCode;
import com.revolut.transference.web.dto.HttpResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Generic error exception when an uncaught error is thrown. It returns an http 500 with a please try again later message.
 */
@Provider
public class UncaughtException extends Throwable implements ExceptionMapper<Throwable> {

    private static final Logger logger = LoggerFactory.getLogger(UncaughtException.class);

    @Override
    public Response toResponse(Throwable exception) {

        logger.error(exception.getMessage(), exception);

        return Response.status(500)
                .entity(new HttpResponseError(ErrorCode.GENERIC_ERROR, "Please try again later"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
