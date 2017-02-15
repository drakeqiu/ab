package com.jarvis.ab.command;

import com.jarvis.ab.entity.Address;
import com.jarvis.ab.service.BaseService;

import java.util.*;

/**
 * Created by qiu.yuan on 2017/2/14.
 */
public class SearchCommand extends BaseCommand{
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
        while (true) {
            System.out.print("by (name|mobile|address):");
            System.out.flush();
            condition = scanner.nextLine();
            if (condition!=null && this.conditionSet.contains(condition.trim())) break;
        }

        System.out.print(condition + ":");
        value = scanner.nextLine();
        List<Address> addresses = baseService.search(condition,value);
        for (Address address: addresses) {
            System.out.println();
            System.out.println("name:" + address.getName());
            System.out.println("mobile: " + address.getMobile());
            System.out.println("address: " + address.getAddress());
            System.out.println();
        }
    }

    public static SearchCommand getInstance(BaseService baseService) {
        SearchCommand instance = new SearchCommand();
        instance.baseService = baseService;
        return instance;
    }

    private final Set<String> conditionSet;
    private SearchCommand(){
        this.conditionSet = new HashSet<String>();
        this.conditionSet.add(NAMECONDITION);
        this.conditionSet.add(MOBILECONDITION);
        this.conditionSet.add(ADDRESSCONDITION);
    }
    private final static String NAMECONDITION = "name";
    private final static String MOBILECONDITION = "mobile";
    private final static String ADDRESSCONDITION = "address";
}
