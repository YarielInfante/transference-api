package com.revolut.transference.web.rest;

import com.revolut.transference.config.ApplicationContext;
import com.revolut.transference.service.ITopUpService;
import com.revolut.transference.web.dto.TopUpDto;
import com.revolut.transference.web.rest.exception.WrongAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Top up Resource.
 *
 * @see ITopUpService
 */
@Path("/customers/topup")
public class TopUpResource {

    private static final Logger logger = LoggerFactory.getLogger(TopUpResource.class);

    /**
     * Top up to an account given the customer id and account
     *
     * @param topUpDto   an instance of {@link TopUpDto}
     * @return http status ok if anything went well
     * @throws WrongAccountException if account id does not belong to the customer id given
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response topup(TopUpDto topUpDto) throws WrongAccountException {

        logger.info("making top up");

        ITopUpService topUpService = ApplicationContext.getApplicationContext().getComponent(ITopUpService.class);

        topUpService.topup(topUpDto);

        return Response.noContent().build();
    }
}
