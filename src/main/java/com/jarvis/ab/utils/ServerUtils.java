package com.jarvis.ab.utils;

import com.jarvis.ab.command.BaseCommand;
import com.jarvis.ab.command.QuitCommand;
import com.jarvis.ab.def.Constants;
import com.jarvis.ab.command.HelpCommand;
import com.jarvis.ab.service.BaseService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jarvis on 10/02/2017.
 */
public class ServerUtils {
    public static Map<String,BaseCommand> loadCommandMap(BaseService baseService) {
        Map<String,BaseCommand> commandMap = new HashMap<String, BaseCommand>();

        try {
            commandMap.put(Constants.HELPCOMMAND,HelpCommand.class.newInstance());
            commandMap.put(Constants.QUITCOMMAND, QuitCommand.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return commandMap;
    }
}
