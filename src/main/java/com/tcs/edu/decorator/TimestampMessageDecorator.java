package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * TimestampMessageDecorator implements a decorator which returns current time with a given message.
 * @author pepe
 */
public class TimestampMessageDecorator {
    public static int messageCount = 0;
    private static final String TIME_HINT = "(Current Time)";

    /**
     * This method decorates a current time string with a given message
     * and changes the value of static variable messageCount
     * @param message Message that comes after current time
     * @return String Returns a string of current time and a message
     */
    public static String decorate(String message) {
        messageCount++;
        return messageCount + " " + Instant.now() + TIME_HINT + " " + message;
    }
}
