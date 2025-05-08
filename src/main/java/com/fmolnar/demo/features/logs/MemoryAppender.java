package com.fmolnar.demo.features.logs;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class MemoryAppender extends ListAppender<ILoggingEvent> {
    public int countLogs() {
        return this.list.size();
    }

    public boolean assertContainsLogAndLevel(String titikakaTo, Level info) {
        return this.list.stream().anyMatch(log -> log.toString().contains(titikakaTo) && log.getLevel().equals(info));
    }
}
