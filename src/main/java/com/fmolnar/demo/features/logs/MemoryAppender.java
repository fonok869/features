package com.fmolnar.demo.features.logs;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public final class MemoryAppender extends ListAppender<ILoggingEvent> {
    public int countLogs() {
        return this.list.size();
    }

    public boolean assertContainsLogAndLevel(String textToHave, Level level) {
        return this.list.stream().anyMatch(log -> log.toString().contains(textToHave) && log.getLevel().equals(level));
    }

    public boolean assertNotContainsLog(String textToCheck) {
        return this.list.stream().noneMatch(log -> log.toString().contains(textToCheck));
    }

    public boolean assertContainsLog(String textToHave) {
        return this.list.stream().anyMatch(log -> log.toString().contains(textToHave));
    }
}
