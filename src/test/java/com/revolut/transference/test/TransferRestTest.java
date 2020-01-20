package com.revolut.transference.test;

import com.revolut.transference.domain.Account;
import com.revolut.transference.domain.Customer;
import com.revolut.transference.web.dto.AccountTopup;
import com.revolut.transference.web.dto.AccountTransfer;
import com.revolut.transference.web.dto.MakeTransfer;
import com.revolut.transference.web.dto.TopUpDto;
import com.revolut.transference.web.rest.CustomerResource;
import com.revolut.transference.web.rest.TopUpResource;
import com.revolut.transference.web.rest.TransferResource;
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

public class TransferRestTest extends JerseyTest {

    private String customerResourcePath = "/customers";


    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        forceSet(TestProperties.CONTAINER_PORT, "0");

        return new ResourceConfig(TopUpResource.class, CustomerResource.class, TransferResource.class);
    }

    @Test
    public void givenCustomerAndAccount_whenTransfer_thenResponseOk() {

        // given
        Customer customer = new Customer("Yariel", "Infante");
        Customer customer2 = new Customer("Yariel", "Infante");

        Response responseCustomer = target(customerResourcePath)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON_TYPE));

        Response responseCustomer2 = target(customerResourcePath)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(customer2, MediaType.APPLICATION_JSON_TYPE));


        String locationHeader = responseCustomer.getHeaderString("location");

        long customerId = Long.valueOf(locationHeader.substring(locationHeader.length() - 1));

        final Response responseSearch = target(customerResourcePath + "/" + customerId).request()
                .get(Response.class);

        Customer customerFound = responseSearch.readEntity(Customer.class);

        Account account = customerFound.getAccounts().stream().findFirst().get();


        String locationHeaderCustomer2 = responseCustomer2.getHeaderString("location");

        long customerId2 = Long.valueOf(locationHeaderCustomer2.substring(locationHeaderCustomer2.length() - 1));

        final Response responseSearchCustomer2 = target(customerResourcePath + "/" + customerId2).request()
                .get(Response.class);

        Customer customerFound2 = responseSearchCustomer2.readEntity(Customer.class);

        Account accountCustomer2 = customerFound2.getAccounts().stream().findFirst().get();

        TopUpDto topup = new TopUpDto();
        topup.setCustomerId(customerId);
        topup.setAccount(new AccountTopup(account.getId()));
        topup.setAmount(new BigDecimal(300));

        Response responseTopup = target(customerResourcePath + "/topup")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(topup, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(HttpStatus.NO_CONTENT_204.getStatusCode(), responseTopup.getStatus());

        // when

        MakeTransfer makeTransfer = new MakeTransfer();
        makeTransfer.setCustomerId(customerId);
        makeTransfer.setAccountOrigin(new AccountTransfer(account.getId(), account.getNumber()));
        makeTransfer.setAccountDestination(new AccountTransfer(accountCustomer2.getId(), accountCustomer2.getNumber()));
        makeTransfer.setAmount(new BigDecimal(100));

        Response transferResponse = target(customerResourcePath + "/trans")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(makeTransfer, MediaType.APPLICATION_JSON_TYPE));

        // then

        assertEquals(HttpStatus.NO_CONTENT_204.getStatusCode(), transferResponse.getStatus());
    }
}
