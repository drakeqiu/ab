package com.jarvis.ab;

import com.jarvis.ab.command.QuitCommand;
import com.jarvis.ab.def.Constants;
import com.jarvis.ab.entity.Address;
import com.jarvis.ab.exception.CommandException;
import com.jarvis.ab.exception.EmptyCommandException;
import com.jarvis.ab.exception.NoCommandException;
import com.jarvis.ab.persistent.AddressBookStoreServiceImpl;
import com.jarvis.ab.service.AddressService;
import com.jarvis.ab.service.BaseService;
import com.jarvis.ab.command.BaseCommand;
import com.jarvis.ab.utils.ServerUtils;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by jarvis on 09/02/2017.
 */
public final class Server {
    private static volatile Server INSTANCE;
    private ExecutorService pool;
    private Scanner scanner;
    private Map<String,BaseCommand> commandMap;
    private List<Address> addressList;
    private BaseService<Address> addressService;

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
        addressService = AddressService.getInstance();
        commandMap = ServerUtils.loadCommandMap(addressService);
        addressService.setAddressBookStoreService(AddressBookStoreServiceImpl.getInstance());
        addressService.load();
        addressList = addressService.findAll();

    }

    public void start() {
        while (true) {
            showPrompt();
            String command = scanner.nextLine();

            try {
                BaseCommand baseCommand = null;
                baseCommand = parse(command);
                if (baseCommand instanceof QuitCommand) {
                    pool.shutdown();
                    addressService.save();
                    break;
                }
                Future future = pool.submit(baseCommand);
                future.get();
            } catch (EmptyCommandException e) {
//                e.printStackTrace();
            } catch (NoCommandException e) {

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    public void showPrompt() {
        System.out.print(Constants.PROMPT);
        System.out.flush();
    }

    public BaseCommand parse(String command) throws EmptyCommandException, NoCommandException {
        // an empty command entered
        if (command == null || "".equals(command.trim())) {
            throw new EmptyCommandException("empty command.");
        }
        BaseCommand inCommand = commandMap.get(command.trim());

        if (inCommand == null) {
            throw new NoCommandException("no such command exists.");
        }

        return inCommand;

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
