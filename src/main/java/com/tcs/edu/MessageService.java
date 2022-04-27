package com.tcs.edu;

import com.tcs.edu.decorator.PostfixDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class MessageService {
    private static final int PAGE_SIZE = 3;
    private static final String PAGE_DIVIDER = "---";

    public static int messageCount = 0;

    public static void log(Message message, Message... messages) {
        if (message != null) printMessage(message);

        for (Message s : filterMessagesFromNull(messages)) {
            printMessage(s);
        }
    }

    public static void log(MessageOrder order, Message message, Message... messages) {
        if (message != null) printMessage(message);

        Message[] nonNullMessages = filterMessagesFromNull(messages);

        sortMessages(order, nonNullMessages);

        for (Message s : nonNullMessages) {
            printMessage(s);
        }
    }

    public static void log(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        if (message != null) printMessage(message);

        Message[] nonNullMessages = filterMessagesFromNull(messages);

        sortMessages(order, nonNullMessages);

        switch (doubling) {
            case DISTINCT -> {
                Message[] printedMessages = new Message[nonNullMessages.length];
                for (int i = 0; i < nonNullMessages.length; i++) {
                    boolean isUnique = true;
                    for (int j = 0; j < i; j++) {
                        if (printedMessages[j].equals(nonNullMessages[i])) {
                            isUnique = false;
                            break;
                        }
                    }
                    if (isUnique) printMessage(nonNullMessages[i]);
                    printedMessages[i] = nonNullMessages[i];
                }
            }
            case DOUBLES -> {
                for (Message s : nonNullMessages) {
                    printMessage(s);
                }
            }
        }
    }

    private static void printMessage(@NotNull Message message) {
        increaseCount();

        String postfix = PostfixDecorator.getPostfixSeverity(message.getSeverity());
        String decoratedMessage = TimestampMessageDecorator.decorate(message.getBody());

        ConsolePrinter.print(decoratedMessage, postfix);

        if (messageCount % PAGE_SIZE == 0) ConsolePrinter.print(PAGE_DIVIDER);
    }

    /**
     * Increases the value of static variable messageCount by 1
     */
    private static void increaseCount() {
        messageCount++;
    }

    private static Message @NotNull [] filterMessagesFromNull(Message... messages) {
        return Arrays.stream(messages).filter(Objects::nonNull).toArray(Message[]::new);
    }

    private static void sortMessages(@NotNull MessageOrder order, Message... messages) {
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
                        if (messages[i - 1].getBody().compareToIgnoreCase(messages[i].getBody()) < 0) {
                            Message temp = messages[i];
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
                        if (messages[i - 1].getBody().compareToIgnoreCase(messages[i].getBody()) > 0) {
                            Message temp = messages[i];
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
