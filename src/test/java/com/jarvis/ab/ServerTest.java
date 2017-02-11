package com.jarvis.ab;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by jarvis on 11/02/2017.
 */

public class ServerTest {
    private Server server;
    @Before
    public void before() {
        server = Server.getINSTANCE();
    }

    @Test
    public void testStart() {

        server.initialize();
//        server.start();
    }
}
