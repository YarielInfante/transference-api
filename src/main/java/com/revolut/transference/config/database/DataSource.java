package com.revolut.transference.config.database;

import com.revolut.transference.util.ApplicationProperties;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Database connection is set here. Here we use a Pool connection named HikariCP
 *
 * @see HikariDataSource
 */
public class DataSource {

    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource();
        ds.setJdbcUrl(ApplicationProperties.getDataSourceUrl());
        ds.setUsername(ApplicationProperties.getDataSourceUser());
        ds.setPassword(ApplicationProperties.getDataSourcePassword());
    }

    /**
     * @return a Connection Object
     * @throws SQLException
     * @see Connection
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
