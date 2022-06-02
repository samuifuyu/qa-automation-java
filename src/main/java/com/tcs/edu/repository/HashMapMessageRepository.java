package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;
import com.tcs.edu.service.Severity;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class HashMapMessageRepository implements MessageRepository {
    private HashMap<UUID, Message> messages = new HashMap<>();

    @Override
    public UUID create(Message message) {
        final UUID key = UUID.randomUUID();
        message.setId(key);
        messages.put(key, message);
        return key;
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return messages.values().stream()
                .filter(m -> m.getSeverity() == by)
                .collect(toList());
    }
}
