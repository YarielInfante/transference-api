package com.revolut.transference.web.rest;

import com.revolut.transference.config.ApplicationContext;
import com.revolut.transference.domain.Customer;
import com.revolut.transference.service.ICustomerService;
import com.revolut.transference.util.ApplicationProperties;
import com.revolut.transference.web.dto.CreateCustomer;
import com.revolut.transference.web.rest.exception.MissingFieldException;
import com.revolut.transference.web.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static com.revolut.transference.mapper.CustomerMapper.toCustomer;

/**
 * Customer Resource. This is a rest controller that exposes /customers endpoint.
 *
 * @see ICustomerService
 */
@Path("/customers")
public class CustomerResource {

    private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);

    /**
     * Create customer and his first account.
     *
     * @param createCustomer an instance of {@link CreateCustomer} having customer's information
     * @return an instance of {@link Response} with HTTP 201 with location of just created entity if everything went well, HTTP 500 otherwise.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CreateCustomer createCustomer) throws MissingFieldException {

        logger.info("creating customer");

        URI uri = null;

        ICustomerService customerService = ApplicationContext.getApplicationContext().getComponent(ICustomerService.class);

        Optional.ofNullable(createCustomer.getName())
                .orElseThrow(MissingFieldException::new);

        Optional.ofNullable(createCustomer.getLastName())
                .orElseThrow(MissingFieldException::new);

        var save = customerService.save(
                Optional.of(createCustomer)
                        .map(toCustomer)
                        .get()
        );


        try {
            uri = new URI(ApplicationProperties.getServerHttpPath() + "/customers/" + save.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Response.created(uri)
                .build();
    }

    /**
     * Find a {@link Customer} by the given id
     *
     * @param id id of Customer to find
     * @return returns the found entity, otherwise a 404 stating it was not found
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") long id) throws NotFoundException {

        logger.info("finding customer");
        var customerService = ApplicationContext.getApplicationContext().getComponent(ICustomerService.class);

        Customer customer = customerService.findById(id);


        return Response.ok(
                Optional.ofNullable(customer)
                        .orElseThrow(NotFoundException::new)
        )
                .build();
    }

}
