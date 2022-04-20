package com.tcs.edu;

import com.tcs.edu.decorator.PostfixDecorator;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.printer.ConsolePrinter;

public class MessageService {
    private static final int PAGE_SIZE = 3;
    private static final String PAGE_DIVIDER = "---";

    public static int messageCount = 0;

    public static void process(Severity severity, String message, String... messages) {
        if (message != null) printMessage(severity, message);

        for (String s : messages) {
            if (s != null) printMessage(severity, s);
        }
    }

    private static void printMessage(Severity severity, String message) {
        increaseCount();

        String postfix = PostfixDecorator.getPostfixSeverity(severity);
        String decoratedMessage = TimestampMessageDecorator.decorate(message);

        ConsolePrinter.print(decoratedMessage, postfix);

        if (messageCount % PAGE_SIZE == 0) ConsolePrinter.print(PAGE_DIVIDER);
    }

    /**
     * Increases the value of static variable messageCount by 1
     */
    public static void increaseCount() {
        messageCount++;
    }
}
