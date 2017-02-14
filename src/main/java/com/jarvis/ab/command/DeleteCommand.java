package com.jarvis.ab.command;

import com.jarvis.ab.service.BaseService;

/**
 * Created by qiu.yuan on 2017/2/14.
 */
public class DeleteCommand extends BaseCommand {
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

    }

    public static DeleteCommand  getInstance(BaseService baseService) {
        DeleteCommand instance = new DeleteCommand();
        instance.baseService = baseService;
        return instance;
    }

    private DeleteCommand(){}
}
