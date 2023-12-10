package com.example.cityswift.server.util;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static final Logger logger = Logger.getLogger("AppLogger");

    static {
        try {
            FileHandler fileHandler = new FileHandler("logfile.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Throwable thrown) {
        logger.log(Level.INFO, message, thrown);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void warning(String message, Throwable thrown) {
        logger.log(Level.WARNING, message, thrown);
    }

    public static void severe(String message) {
        logger.severe(message);
    }

    public static void severe(String message, Throwable thrown) {
        logger.log(Level.SEVERE, message, thrown);
    }
}
