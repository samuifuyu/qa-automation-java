package com.tcs.edu.domain;

import com.tcs.edu.decorator.Severity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.tcs.edu.decorator.Severity.MINOR;

public class Message {
    private String body;
    private Severity severity;

    public Message(Severity severity, String body) {
        this.body = body;
        this.severity = severity;
    }

    public Message(String body) {
        this.body = body;
        this.severity = MINOR;
    }

    public String getBody() {
        return body;
    }

    public Severity getSeverity() {
        return severity;
    }

    public boolean equals(@NotNull Message message) {
        return Objects.equals(message.getBody(), body) && message.getSeverity() == severity;
    }
}
