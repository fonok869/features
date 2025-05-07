package com.fmolnar.demo.features.logs;


import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.logging.log4j.Level.INFO;

class ServiceToLog {

    private static Logger LOGGER = LoggerFactory.getLogger("BASIC");

    public void putAllLogs(String logMessage, Level level) {
        if (level == INFO) {
            LOGGER.info(logMessage);
        }
    }
}
