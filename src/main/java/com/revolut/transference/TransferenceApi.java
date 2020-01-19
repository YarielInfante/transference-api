package com.revolut.transference;

import com.revolut.transference.config.server.IServer;
import com.revolut.transference.config.server.impl.GrizzlyHttpServer;


public class TransferenceApi {

    public static void main(String... args) {
        IServer server = new GrizzlyHttpServer();
        server.start();
    }
}
