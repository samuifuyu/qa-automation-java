package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * TimestampMessageDecorator implements a decorator which returns current time with a given message.
 * @author pepe
 */
public class TimestampMessageDecorator {
    /**
     * This method decorates a current time string with a given message
     * @param message Message that comes after current time
     * @return String Returns a string of current time and a message
     */
    public static String decorate(String message) {
        return Instant.now() + " " + message;
    }
}
