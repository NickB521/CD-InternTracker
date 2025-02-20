package com.codedifferently.CD_InternTracker.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingConfig {

    private static final Logger logger
            = LoggerFactory.getLogger(LoggingConfig.class);

    public static void main(String[] args) {
        logger.info("Example log from {}", LoggingConfig.class.getSimpleName());
    }
}
