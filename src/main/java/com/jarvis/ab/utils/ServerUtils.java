package com.jarvis.ab.utils;

import com.jarvis.ab.command.*;
import com.jarvis.ab.def.Constants;
import com.jarvis.ab.service.BaseService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jarvis on 10/02/2017.
 */
public class ServerUtils {

    /**
     *
     * @param baseService
     * @return
     */
    public static Map<String,BaseCommand> loadCommandMap(BaseService baseService) {
        Map<String,BaseCommand> commandMap = new HashMap<String, BaseCommand>();

        commandMap.put(Constants.HELPCOMMAND,HelpCommand.getInstance(baseService));
        commandMap.put(Constants.QUITCOMMAND, QuitCommand.getInstance(baseService));
        commandMap.put(Constants.DELETECOMMAND, DeleteCommand.getInstance(baseService));
        commandMap.put(Constants.FINDCOMMAND, FindCommand.getInstance(baseService));
        commandMap.put(Constants.ADDCOMMAND, AddCommand.getInstance(baseService));

        return commandMap;
    }
}
