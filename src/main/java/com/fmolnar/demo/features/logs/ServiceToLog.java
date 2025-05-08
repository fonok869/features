package com.fmolnar.demo.features.logs;


import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.classic.Level.*;

class ServiceToLog {

    private static Logger LOGGER = LoggerFactory.getLogger("BASIC");

    public void putAllLogs(String logMessage, Level level) {
        if (INFO.equals(level)) {
            LOGGER.info(logMessage);
        } else if (WARN.equals(level)) {
            LOGGER.warn(logMessage);
        } else if (ERROR.equals(level)) {
            LOGGER.error(logMessage);
        }
    }
}
