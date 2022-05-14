package com.tcs.edu.domain;

import com.tcs.edu.service.Severity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.tcs.edu.service.Severity.MINOR;

public class Message implements Comparable<Message> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(body, message.body) && severity == message.severity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, severity);
    }

    @Override
    public int compareTo(@NotNull Message o) {
        return body.compareTo(o.getBody());
    }

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                ", severity=" + severity +
                '}';
    }
}
