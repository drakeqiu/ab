package com.jarvis.ab.command;

import com.jarvis.ab.entity.Address;
import com.jarvis.ab.service.BaseService;

import java.util.Scanner;

/**
 * Created by qiu.yuan on 2017/2/14.
 */
public class AddCommand extends BaseCommand{
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
        System.out.print("name:");
        System.out.flush();
        String name = scanner.nextLine();
        System.out.print("mobile:");
        System.out.flush();
        String mobile = scanner.nextLine();
        System.out.print("address:");
        System.out.flush();
        String address = scanner.nextLine();
        Address addressEntity = new Address();
        addressEntity.setName(name.trim());
        addressEntity.setMobile(mobile.trim());
        addressEntity.setAddress(address.trim());
        this.baseService.add(addressEntity);
        System.out.println("address entry added");
        System.out.flush();
    }

    public static AddCommand  getInstance(BaseService baseService) {
        AddCommand instance = new AddCommand();
        instance.baseService = baseService;
        return instance;
    }

    private AddCommand(){}
}
