package org.example; // Compliant

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsafeLogger {
    private static final Logger logger = LoggerFactory.getLogger(UnsafeLogger.class);

    public void logUserAction(String userInput) {
        logger.info("User performed action: " + userInput); // 🔥 Untrusted input in logs
    }
}
