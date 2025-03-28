package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A wrapper class for SLF4J Logger to provide simplified logging methods.
 */
public class LoggerWrapper {

    private final Logger logger;

    /**
     * Constructs a LoggerWrapper for the specified class.
     *
     * @param clazz the class for which the logger is created
     */
    public LoggerWrapper(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }
    
    /**
     * Logs an informational message.
     *
     * @param message the message to log
     */
    public void info(String message) {
        logger.info(message);
    }
    
    /**
     * Logs an informational message with parameters.
     *
     * @param message the message to log containing placeholders
     * @param args    the arguments to replace placeholders in the message
     */
    public void info(String message, Object... args) {
        logger.info(message, args);
    }
    
    /**
     * Logs a debug message.
     *
     * @param message the message to log
     */
    public void debug(String message) {
        logger.debug(message);
    }
    
    /**
     * Logs a warning message.
     *
     * @param message the message to log
     */
    public void warn(String message) {
        logger.warn(message);
    }
    
    /**
     * Logs an error message along with an exception.
     *
     * @param message the error message
     * @param t       the exception to log
     */
    public void error(String message, Throwable t) {
        logger.error(message, t);
    }
    
    // Additional wrapper methods can be added as needed.
}
