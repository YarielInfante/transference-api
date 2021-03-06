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
 * When a required field is missing this exception is thrown. It returns a http 400 with a missing required fields message.
 */
@Provider
public class MissingFieldException extends Exception implements ExceptionMapper<MissingFieldException> {

    private static final Logger logger = LoggerFactory.getLogger(MissingFieldException.class);

    @Override
    public Response toResponse(MissingFieldException e) {

        logger.error(e.getMessage(), e);

        return Response.status(400)
                .entity(new HttpResponseError(ErrorCode.MISSING_REQUIRED_FIELDS, "Missing required fields"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
