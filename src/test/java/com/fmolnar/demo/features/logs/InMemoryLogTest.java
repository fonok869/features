package com.fmolnar.demo.features.logs;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryLogTest {

    private static final String LOGGER_NAME = "BASIC";
    private final MemoryAppender memoryAppender = new MemoryAppender();

    @BeforeEach
    void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);

        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        memoryAppender.start();
    }

    @Test
    void shouldCountOneLog() {
        new ServiceToLog().putAllLogs("TitikakaTo", org.apache.logging.log4j.Level.INFO);
        assertThat(memoryAppender.countLogs()).isEqualTo(1);
    }

    @AfterEach
    void tearDown() {
        memoryAppender.stop();
    }
}
