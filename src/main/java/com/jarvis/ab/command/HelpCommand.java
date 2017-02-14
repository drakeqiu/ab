package com.jarvis.ab.command;

import com.jarvis.ab.service.BaseService;

/**
 * Created by jarvis on 10/02/2017.
 */
public class HelpCommand extends BaseCommand {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("This is a small application for a address book.");
        System.out.println("It support following commands:");
        System.out.println("\tadd: use add command to add new address entry.");
        System.out.println("\tsearch: use search command to get one or more address entries.");
        System.out.println("\tremove: use remove command to remove one or more address entries.");
        System.out.println("\t!help: get help");
        System.out.flush();
    }

    public static HelpCommand  getInstance(BaseService baseService) {
        HelpCommand instance = new HelpCommand();
        instance.baseService = baseService;
        return instance;
    }

    private HelpCommand(){}
}
