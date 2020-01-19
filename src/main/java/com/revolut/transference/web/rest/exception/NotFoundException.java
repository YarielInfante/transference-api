package com.revolut.transference.web.rest.exception;

import com.revolut.transference.web.dto.ErrorCode;
import com.revolut.transference.web.dto.HttpResponseError;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * When an entity is not found this exception is thrown. It returns a http 404 with a ID not found message.
 */
@Provider
public class NotFoundException extends Exception implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {

        return Response.status(404)
                .entity(new HttpResponseError(ErrorCode.NOT_FOUND, "ID not found"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
