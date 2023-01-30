package com.cmbookrental.prj.comm;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    // Singleton 패턴으로 구현
    Logger logger = Logger.getLogger("mylogger");
    private static MyLogger instance = new MyLogger();

    public static final String errorLog = "log.txt";

    private FileHandler logFile = null;

    private MyLogger() {
        try {
            logFile = new FileHandler(errorLog, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logFile.setFormatter(new SimpleFormatter());
        logger.addHandler(logFile);
    }
    public static MyLogger getLogger(){
        return instance;
    }

    public void log(String msg) {
        logger.warning(msg);
    }

    public void warning(String msg){
        logger.warning(msg);
    }
}


