package com.anton31413.util;

import ch.qos.logback.classic.*;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@UtilityClass
public class LoggerConfig {

    public static void configure() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        // добавил highlight, чтобы были цветные уровни
        encoder.setPattern("%d{HH:mm:ss} %highlight(%-5level) %logger{36} - %msg%n");
        encoder.start();

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(context);
        consoleAppender.setEncoder(encoder);
        consoleAppender.start();

        // Root Logger
        ch.qos.logback.classic.Logger rootLogger =
                context.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.TRACE);
        rootLogger.addAppender(consoleAppender);

        // Hibernate общие логи
        ch.qos.logback.classic.Logger hibernateLogger =
                context.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.WARN);
        hibernateLogger.addAppender(consoleAppender);
        hibernateLogger.setAdditive(false); // отключаем наследование rootLogger

        // SQL лог Hibernate
        ch.qos.logback.classic.Logger sqlLogger =
                context.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.DEBUG);
        sqlLogger.addAppender(consoleAppender);
        sqlLogger.setAdditive(false); // отключаем наследование

        // Параметры SQL (binding)
        ch.qos.logback.classic.Logger bindLogger =
                context.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        bindLogger.setLevel(Level.TRACE);
        bindLogger.addAppender(consoleAppender);
        bindLogger.setAdditive(false); // отключаем наследование
    }
}

