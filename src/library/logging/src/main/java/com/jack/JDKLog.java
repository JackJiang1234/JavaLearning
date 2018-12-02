package com.jack;

import java.util.logging.Logger;

public class JDKLog {
    public static void main(String[] args){
        Logger logger = Logger.getLogger(JDKLog.class.getName());
        logger.info("Hello World.");
        logger.warning("warning");
    }
}
