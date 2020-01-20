package com.revolut.transference.test;

import com.revolut.transference.domain.Account;
import com.revolut.transference.domain.Customer;
import com.revolut.transference.web.dto.AccountTopup;
import com.revolut.transference.web.dto.TopUpDto;
import com.revolut.transference.web.rest.CustomerResource;
import com.revolut.transference.web.rest.TopUpResource;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TopUpRestTest extends JerseyTest {

    private String customerResourcePath = "/customers";

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        forceSet(TestProperties.CONTAINER_PORT, "0");

        return new ResourceConfig(TopUpResource.class, CustomerResource.class);
    }

    @Test
    public void givenCustomerAndAccount_whenTopup_thenResponseOk() {

        // given
        var customer = new Customer("Yariel", "Infante");

        var response = target(customerResourcePath)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON_TYPE));


        String locationHeader = response.getHeaderString("location");

        long customerId = Long.valueOf(locationHeader.substring(locationHeader.length() - 1));

        final Response responseSearch = target(customerResourcePath + "/" + customerId).request()
                .get(Response.class);

        var customerFound = responseSearch.readEntity(Customer.class);

        // when
        Account account = customerFound.getAccounts().stream().findFirst().get();

        var topup = new TopUpDto();
        topup.setCustomerId(customerId);
        topup.setAccount(new AccountTopup(account.getId()));
        topup.setAmount(new BigDecimal(300));

        var responseTopup = target(customerResourcePath + "/topup")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(topup, MediaType.APPLICATION_JSON_TYPE));

        // then
        assertEquals(HttpStatus.NO_CONTENT_204.getStatusCode(), responseTopup.getStatus());

    }

}
