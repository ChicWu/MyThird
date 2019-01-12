package com.zhibei.util;

import org.apache.log4j.Logger;

public class Log {
    private static Logger log;
    public static long start;
    
    static {
        log = Logger.getLogger(Log.class);
    }

    private Log() { }

    public static void debug(Object message){
        log.debug(message);
    }

    public static void info(Object message){
        log.info(message);
    }

    public static void warn(Object message){
        log.warn(message);
    }

    public static void error(Object message){
        log.error(message);
    }

    public static void fatal (Object message){
        log.fatal(message);
    }

    public static Logger getLog(){
        return log;
    }

}
