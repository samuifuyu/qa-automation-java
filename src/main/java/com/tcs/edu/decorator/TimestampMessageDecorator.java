package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * TimestampMessageDecorator implements a decorator which returns current time with a given message.
 * @author pepe
 */
public class TimestampMessageDecorator {
    public static int messageCount = 0;
    private static final int PAGE_SIZE = 2;
    private static final String TIME_HINT = "(Current Time)";
    private static final String MESSAGE_FORMAT = "%d: %s %s %s";
    private static final String MESSAGE_FORMAT_WITH_DIVIDER = "%d: %s %s %s\n---";

    /**
     * This method decorates a current time string with a given message
     * @param message Message that comes after current time
     * @return String Returns a string of current time and a message
     */
    public static String decorate(String message) {
        increaseCount();
        return messageCount % PAGE_SIZE == 0?
                String.format(MESSAGE_FORMAT_WITH_DIVIDER, messageCount, Instant.now(), TIME_HINT, message) :
                String.format(MESSAGE_FORMAT, messageCount, Instant.now(), TIME_HINT, message);
    }

    /**
     * Increases the value of static variable messageCount by 1
     */
    public static void increaseCount() {
        messageCount++;
    }
}
