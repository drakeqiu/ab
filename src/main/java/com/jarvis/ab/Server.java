package com.jarvis.ab;

import com.jarvis.ab.def.Constants;

import java.util.Scanner;

/**
 * Created by jarvis on 09/02/2017.
 */
public class Server {
    private static volatile Server  INSTANCE;
    public static Server getINSTANCE() {
        synchronized (Server.class) {
            if (INSTANCE == null) {
                synchronized (Server.class) {
                    INSTANCE = new Server();
                }
            }
        }
        return INSTANCE;

    }

    private Server(){}

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showPrompt();
            String command = scanner.next();
        }
    }

    public void showPrompt() {
        System.out.print(Constants.PROMPT);
    }
}
