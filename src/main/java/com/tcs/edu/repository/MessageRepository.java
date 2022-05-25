package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;
import com.tcs.edu.service.Severity;

import java.util.Collection;
import java.util.UUID;

public interface MessageRepository {
    UUID create(Message message);
    Message findByPrimaryKey(UUID key);
    Collection<Message> findAll();
    Collection<Message> findBySeverity(Severity by);
}
