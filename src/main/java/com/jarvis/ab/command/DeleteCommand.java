package com.jarvis.ab.command;

import com.jarvis.ab.entity.Address;
import com.jarvis.ab.service.BaseService;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by qiu.yuan on 2017/2/14.
 */
public class DeleteCommand extends BaseCommand {
    private final Set<String> conditionSet;
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
        Scanner scanner = new Scanner(System.in);
        String condition;
        String value;
        int count = 0;
        while (true) {
            System.out.print("by (name|mobile|address):");
            System.out.flush();
            condition = scanner.nextLine();
            if (condition!=null && this.conditionSet.contains(condition.trim())) break;
        }

        System.out.print(condition + ":");
        value = scanner.nextLine();
        count = baseService.delete(condition,value);
        if (count > 1) {
            System.out.println(count + " entries deleted.");
        } else {
            System.out.println(count + " entry deleted.");
        }
    }

    public static DeleteCommand  getInstance(BaseService baseService) {
        DeleteCommand instance = new DeleteCommand();
        instance.baseService = baseService;
        return instance;
    }

    /**
     * default constructor for initializing condition command set.
     */
    private DeleteCommand(){
        this.conditionSet = new HashSet<String>();
        this.conditionSet.add(NAMECONDITION);
        this.conditionSet.add(MOBILECONDITION);
        this.conditionSet.add(ADDRESSCONDITION);
    }

    private final static String NAMECONDITION = "name";
    private final static String MOBILECONDITION = "mobile";
    private final static String ADDRESSCONDITION = "address";
}
