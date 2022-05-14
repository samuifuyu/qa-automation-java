package com.tcs.edu.service;

import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.Printer;
import com.tcs.edu.service.validation.ValidatedService;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class OrderedDistinctMessageService extends ValidatedService implements MessageService {

    private static final int PAGE_SIZE = 3;
    private static final String PAGE_DIVIDER = "---";
    public static int messageCount = 0;
    private final Printer printer;
    private final MessageDecorator<String> timestampMessageDecorator;
    private final MessageDecorator<Severity> postfixDecorator;

    public OrderedDistinctMessageService(
            Printer printer,
            MessageDecorator<String> timestampMessageDecorator,
            MessageDecorator<Severity> postfixDecorator
    ) {
        this.printer = printer;
        this.timestampMessageDecorator = timestampMessageDecorator;
        this.postfixDecorator = postfixDecorator;
    }

    /**
     * Increases the value of static variable messageCount by 1
     */
    private static void increaseCount() {
        messageCount++;
    }

    @Override
    public void log(Message message, Message... messages) {
        if (!super.isArgsValid(messages) || !super.isArgsValid(message)) throw new IllegalArgumentException();

//        if (message != null) printMessage(message);
//        Arrays.stream(filterMessagesFromNull(messages)).forEach(this::printMessage);
        printMessage(message);
        Arrays.stream(messages).forEach(this::printMessage);
    }

    @Override
    public void log(MessageOrder order, Message message, Message... messages) {
        if (!super.isArgsValid(messages) || !super.isArgsValid(order, message))
            throw new IllegalArgumentException();

//        if (message != null) printMessage(message);
//        Message[] nonNullMessages = filterMessagesFromNull(messages);
//        sortMessages(order, nonNullMessages);
//        Arrays.stream(nonNullMessages).forEach(this::printMessage);
        printMessage(message);
        sortMessages(order, messages);
        Arrays.stream(messages).forEach(this::printMessage);
    }

    @Override
    public void log(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        if (!super.isArgsValid(order, doubling, message) || !super.isArgsValid(messages))
            throw new IllegalArgumentException();

//        if (message != null) printMessage(message);
//        Message[] nonNullMessages = filterMessagesFromNull(messages);
//        sortMessages(order, nonNullMessages);
//        switch (doubling) {
//            case DISTINCT -> Arrays.stream(distinct(nonNullMessages)).forEach(this::printMessage);
//            case DOUBLES -> Arrays.stream(nonNullMessages).forEach(this::printMessage);
//        }

        printMessage(message);
        sortMessages(order, messages);
        switch (doubling) {
            case DISTINCT -> Arrays.stream(distinct(messages)).forEach(this::printMessage);
            case DOUBLES -> Arrays.stream(messages).forEach(this::printMessage);
        }
    }

    private Message @NotNull [] distinct(Message[] messages) {
        return Arrays.stream(messages).distinct().toArray(Message[]::new);
    }

    private void printMessage(@NotNull Message message) {
        increaseCount();

        String postfix = postfixDecorator.decorate(message.getSeverity());
        String decoratedMessage = timestampMessageDecorator.decorate(message.getBody());

        printer.print(decoratedMessage, postfix);

        if (messageCount % PAGE_SIZE == 0) printer.print(PAGE_DIVIDER);
    }
}
