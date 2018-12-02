package com.jack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAndLogbackTest {
    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(SimpleAndLogbackTest.class);
        logger.trace("Trace Level.");
        logger.debug("Debug Level.");
        logger.info("Info Level.");
        logger.warn("Warn Level.");
        logger.error("Error Level.");
    }
}
