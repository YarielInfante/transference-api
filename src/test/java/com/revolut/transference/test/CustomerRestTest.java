package com.revolut.transference.test;

import com.revolut.transference.domain.Customer;
import com.revolut.transference.web.rest.CustomerResource;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class CustomerRestTest extends JerseyTest {

    private long customerId = 0;
    private String customerResourcePath = "/customers";

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        forceSet(TestProperties.CONTAINER_PORT, "0");

        return new ResourceConfig(CustomerResource.class);
    }

    @Test
    public void givenCreateCustomer_whenCreated_thenResponseIsOkAndValid() {

        // given
        var customer = new Customer("Yariel", "Infante");

        // when
        var response = target(customerResourcePath)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON_TYPE));

        // then
        assertEquals("Http Response should be 200 ", HttpStatus.CREATED_201.getStatusCode(), response.getStatus());

        assertNotNull("location header not returned", response.getHeaderString("location"));

        String locationHeader = response.getHeaderString("location");

        customerId = Long.valueOf(locationHeader.substring(locationHeader.length() - 1));

        assertTrue("ID not returned", customerId > 0);

    }


    @Test
    public void givenCustomerId_whenSearch_thenCustomerFoundOk() {

        // given
        final long customerId = this.customerId;

        // when
        final Response response = target(customerResourcePath + "/" + customerId).request()
                .get(Response.class);

        // then
        assertEquals("http code must be 200 ok", HttpStatus.OK_200.getStatusCode(), response.getStatus());
        assertEquals("ID returned does not match the given one", customerId, response.readEntity(Customer.class).getId());

    }
}
