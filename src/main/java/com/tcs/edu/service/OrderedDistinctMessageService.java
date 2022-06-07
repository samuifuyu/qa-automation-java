package com.tcs.edu.service;

import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.service.validation.ValidatedService;
import org.jetbrains.annotations.NotNull;
import com.tcs.edu.repository.MessageRepository;

import java.util.Arrays;

import static com.tcs.edu.service.Doubling.DISTINCT;

public class OrderedDistinctMessageService extends ValidatedService implements MessageService {

    private static final int PAGE_SIZE = 3;
    private static final String PAGE_DIVIDER = "---";
    public static int messageCount = 0;
    private final MessageRepository messageRepository;

    public OrderedDistinctMessageService(
            MessageRepository messageRepository
    ) {
        this.messageRepository = messageRepository;
    }

    /**
     * Increases the value of static variable messageCount by 1
     */
    public static void increaseCount() {
        messageCount++;
    }

    @Override
    public void log(Message message, Message... messages) {

        try {
            super.isArgsValid(messages);
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException("Invalid arguments", e);
        }

        messageRepository.create(message);
        Arrays.stream(messages).forEach(messageRepository::create);
    }

    @Override
    public void log(MessageOrder order, Message message, Message... messages) {
        try {
            super.isArgsValid(order);
        } catch (IllegalArgumentException e) {
            throw new LogException("Invalid arguments", e);
        }

        sortMessages(order, messages);
        log(message, messages);
    }

    @Override
    public void log(Doubling doubling, Message message, Message... messages) {
        try {
            super.isArgsValid(doubling);
        } catch (IllegalArgumentException e) {
            throw new LogException("Invalid arguments", e);
        }

        log(message, resolveDoubling(doubling, messages));
    }

    @Override
    public void log(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        try {
            super.isArgsValid(order, doubling);
        } catch (IllegalArgumentException e) {
            throw new LogException("Invalid arguments", e);
        }

        sortMessages(order, messages);
        log(message, resolveDoubling(doubling, messages));
    }

    private Message @NotNull [] distinct(Message[] messages) {
        return Arrays.stream(messages).distinct().toArray(Message[]::new);
    }

    private Message @NotNull [] resolveDoubling(Doubling doubling, Message[] messages) {
        return doubling == DISTINCT? distinct(messages) : messages;
    }
}
