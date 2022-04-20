package com.tcs.edu;

import com.tcs.edu.decorator.PostfixDecorator;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.printer.ConsolePrinter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static com.tcs.edu.Doubling.DISTINCT;
import static com.tcs.edu.MessageOrder.ASC;
import static com.tcs.edu.MessageOrder.DESC;

public class MessageService {
    private static final int PAGE_SIZE = 3;
    private static final String PAGE_DIVIDER = "---";

    public static int messageCount = 0;

    public static void process(Severity severity, MessageOrder order, Doubling doubling, String message, String... messages) {
        if (message != null) printMessage(severity, message);

        String[] nonNullMessages = Arrays.stream(messages).filter(Objects::nonNull).toArray(String[]::new);

        sortMessages(order, nonNullMessages);

        switch (doubling) {
            case DISTINCT -> {
                String[] printedMessages = new String[nonNullMessages.length];
                for (int i = 0; i < nonNullMessages.length; i++) {
                    boolean isUnique = true;
                    for (int j = 0; j < i; j++) {
                        if (Objects.equals(printedMessages[j], nonNullMessages[i])) {
                            isUnique = false;
                            break;
                        }
                    }
                    if (isUnique) printMessage(severity, nonNullMessages[i]);
                    printedMessages[i] = nonNullMessages[i];
                }
            }
            case DOUBLES -> {
                for (String s: nonNullMessages) {
                    printMessage(severity, s);
                }
            }
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
    private static void increaseCount() {
        messageCount++;
    }

    private static void sortMessages(@NotNull MessageOrder order, String... messages) {
//        так лучше =(
//        switch (order) {
//            case DESC -> Arrays.sort(messages, Comparator.nullsFirst(Comparator.reverseOrder()));
//            case ASC -> Arrays.sort(messages, Comparator.nullsFirst(Comparator.naturalOrder()));
//        }

        boolean notSorted = true;
        switch (order) {
            case DESC -> {
                while (notSorted) {
                    notSorted = false;
                    for (int i = 1; i < messages.length; i++) {
                        if (messages[i - 1].compareToIgnoreCase(messages[i]) < 0) {
                            String temp = messages[i];
                            messages[i] = messages[i - 1];
                            messages[i - 1] = temp;

                            notSorted = true;
                        }
                    }
                }
            }
            case ASC -> {
                while (notSorted) {
                    notSorted = false;
                    for (int i = 1; i < messages.length; i++) {
                        if (messages[i - 1].compareToIgnoreCase(messages[i]) > 0) {
                            String temp = messages[i];
                            messages[i] = messages[i - 1];
                            messages[i - 1] = temp;

                            notSorted = true;
                        }
                    }
                }
            }
        }
    }
}
