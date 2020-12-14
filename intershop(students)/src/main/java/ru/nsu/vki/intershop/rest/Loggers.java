package ru.nsu.vki.intershop.rest;

import org.apache.log4j.PropertyConfigurator;

public class Loggers {
    private static final org.apache.log4j.Logger LOGGER;

    static {
        PropertyConfigurator.configure(Loggers.class.getClassLoader().getResourceAsStream("log4.properties"));
        LOGGER = org.apache.log4j.Logger.getLogger(Loggers.class.getName());
    }

    public static org.apache.log4j.Logger getLogger() {
        return LOGGER;
    }

    public static void error(String message, Throwable t) {
        getLogger().error(message, t);
    }

    public static void debug(String message, Throwable t) {
        getLogger().debug(message, t);
    }

    public static void warn(String message, Throwable t) {
        getLogger().warn(message, t);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void debug(String message) {
        getLogger().debug(message);
    }
}
