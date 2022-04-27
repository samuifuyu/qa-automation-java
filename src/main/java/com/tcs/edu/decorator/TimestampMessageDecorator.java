package com.tcs.edu.decorator;

import java.time.Instant;

import static com.tcs.edu.MessageService.messageCount;

/**
 * TimestampMessageDecorator implements a decorator which returns current time with a given message.
 *
 * @author pepe
 */
public class TimestampMessageDecorator {
    private static final String TIME_HINT = "(Current Time)";
    private static final String MESSAGE_FORMAT = "%d: %s %s %s";

    /**
     * This method decorates a current time string with a given message
     *
     * @param message Message that comes after current time
     * @return String Returns a string of current time and a message
     */
    public static String decorate(String message) {
        return String.format(MESSAGE_FORMAT, messageCount, Instant.now(), TIME_HINT, message);
    }
}
