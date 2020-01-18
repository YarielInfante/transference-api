package com.revolut.transference;

import com.revolut.transference.config.server.IServer;
import com.revolut.transference.config.server.impl.GrizzlyHttpServer;
import com.revolut.transference.util.ApplicationProperties;

import java.sql.SQLException;

public class TransferenceApi {

    public static void main(String... args) throws SQLException {
        ApplicationProperties.load();
        IServer server = new GrizzlyHttpServer();
        server.start();
    }
}
