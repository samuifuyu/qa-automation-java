package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public interface MessageService {

    void log(Message message, Message... messages);

    void log(MessageOrder order, Message message, Message... messages);

    void log(Doubling doubling, Message message, Message... messages);

    void log(MessageOrder order, Doubling doubling, Message message, Message... messages);

    default void sortMessages(@NotNull MessageOrder order, Message... messages) {
        switch (order) {
            case DESC -> Arrays.sort(messages, Comparator.nullsFirst(Comparator.reverseOrder()));
            case ASC -> Arrays.sort(messages, Comparator.nullsFirst(Comparator.naturalOrder()));
        }
    }
}
