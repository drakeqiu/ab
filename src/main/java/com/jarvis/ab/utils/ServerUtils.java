package com.jarvis.ab.utils;

import com.jarvis.ab.def.Constants;
import com.jarvis.ab.service.HelpCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jarvis on 10/02/2017.
 */
public class ServerUtils {
    public static Map<String,Class> loadCommandMap() {
        Map<String,Class> commandMap = new HashMap<String, Class>();
        commandMap.put(Constants.HELPCOMMAND,HelpCommand.class);
        return commandMap;
    }
}
