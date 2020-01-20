package com.revolut.transference.web.rest;

import com.revolut.transference.config.ApplicationContext;
import com.revolut.transference.service.ITransferService;
import com.revolut.transference.web.dto.MakeTransfer;
import com.revolut.transference.web.rest.exception.InsufficientBalanceException;
import com.revolut.transference.web.rest.exception.WrongAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/trans")
public class TransferResource {

    private static final Logger logger = LoggerFactory.getLogger(TransferResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transfer(MakeTransfer transfer) throws WrongAccountException, InsufficientBalanceException {

        logger.info("making transfer");

        var transferService = ApplicationContext.getApplicationContext().getComponent(ITransferService.class);

        transferService.makeTransfer(transfer);

        return Response.noContent().build();
    }
}
