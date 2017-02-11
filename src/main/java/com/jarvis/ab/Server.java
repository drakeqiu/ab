package com.jarvis.ab;

import com.jarvis.ab.def.Constants;
import com.jarvis.ab.entity.Address;
import com.jarvis.ab.exception.EmptyCommandException;
import com.jarvis.ab.service.CommandService;
import com.jarvis.ab.utils.ServerUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jarvis on 09/02/2017.
 */
public final class Server {
    private static volatile Server  INSTANCE;
    private ExecutorService pool;
    private Scanner scanner;
    private Map<String,Class> commandMap;
    private List<Address> addressList;

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

    private void loadAB() {

    }
    public void initialize() {
        pool = Executors.newSingleThreadExecutor();
        scanner = new Scanner(System.in);
        commandMap = ServerUtils.loadCommandMap();
        addressList = new ArrayList<Address>();

    }

    public void start() {
        while (true) {
            showPrompt();
            String command = scanner.nextLine();

            try {
                CommandService commandService = null;
                commandService = parse(command);
                pool.submit(commandService);
            } catch (EmptyCommandException e) {
//                e.printStackTrace();
            }

        }
    }

    public void showPrompt() {
        System.out.print(Constants.PROMPT);
        System.out.flush();
    }

    public CommandService parse(String command) throws EmptyCommandException {
        // an empty command entered
        if (command == null || "".equals(command.trim())) {
            throw new EmptyCommandException("empty command.");
        }
        Class clazz = commandMap.get(command.trim());

        try {
            return (CommandService) clazz.newInstance();
        } catch (InstantiationException e) {
            // e.printStackTrace();
        } catch (IllegalAccessException e) {
            // e.printStackTrace();
        }
        return null;
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
