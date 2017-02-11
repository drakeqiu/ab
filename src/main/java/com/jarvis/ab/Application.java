package com.jarvis.ab;

/**
 * Hello world!
 */
public class Application {
    public static void main(String[] args) {
        Server server = Server.getINSTANCE();
        server.initialize();
        server.start();
    }
}
