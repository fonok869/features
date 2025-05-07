package com.fmolnar.demo.features.logs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class MemoryAppender extends ListAppender<ILoggingEvent> {
    public int countLogs() {
        return this.list.size();
    }
}
