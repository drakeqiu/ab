package com.jarvis.ab;

import com.jarvis.ab.def.Constants;

import java.util.Scanner;

/**
 * Created by jarvis on 09/02/2017.
 */
public final class Server {
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
            String command = scanner.nextLine();
            try {
                parse(command);
                //showHelp();

            }catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    public void showPrompt() {
        System.out.print(Constants.PROMPT);
        System.out.flush();
    }

    public void parse(String command) {
        // an empty command entered
        if (command == null || "".equals(command.trim())) {
            throw new RuntimeException("empty command received.");
        }
    }

    public void showHelp() {
        System.out.println("This is a small application for a address book.");
        System.out.println("It support following commands:");
        System.out.println("\tadd: use add command to add new address entry.");
        System.out.println("\tsearch: use search command to get one or more address entries.");
        System.out.println("\tremove: use remove command to remove one or more address entries.");
        System.out.println("\t!help: get help");
        System.out.flush();
    }
}
