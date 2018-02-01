package com.test.fernandosouto.superheroes.mock.fixtures;


import com.test.fernandosouto.superheroes.mock.MockServer;

import java.io.IOException;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class ServerFixtures {

    //marvel api
    public static final String MARVEL_API_RESPONSE = "marvelApi.json";

    public static void enqueueServerFile(MockServer server, String file) throws IOException {
        server.enqueueFile(200, file);
    }

    public static void enqueueServerError(MockServer server, int code) {
        server.enqueue(code);
    }
}
