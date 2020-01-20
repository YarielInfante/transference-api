package com.revolut.transference.mapper;

import com.revolut.transference.domain.Customer;
import com.revolut.transference.web.dto.CreateCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class CustomerMapper {

    public static Function<CreateCustomer, Customer> toCustomer = createCustomer -> new Customer(createCustomer.getName(), createCustomer.getLastName());

    public static Function<ResultSet, Customer> resultSetToCustomer = resultSet -> {
        try {
            return new Customer(
                    resultSet.getLong("CUSTOMER_ID"),
                    resultSet.getString("CUSTOMER_NAME"),
                    resultSet.getString("CUSTOMER_LAST_NAME")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    };

}
