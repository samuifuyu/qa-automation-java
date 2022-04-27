package com.tcs.edu;

import com.tcs.edu.domain.Message;

import static com.tcs.edu.Doubling.DISTINCT;
import static com.tcs.edu.MessageOrder.ASC;
import static com.tcs.edu.MessageOrder.DESC;
import static com.tcs.edu.MessageService.log;
import static com.tcs.edu.decorator.Severity.*;

class Application {
    public static void main(String[] args) {

        log(
                DESC,
                DISTINCT,
                new Message(MAJOR, "hello 1"),
                new Message(MAJOR, "hello 111"),
                new Message(MINOR, "hello 3"),
                new Message(REGULAR, "hello 2"),
                new Message(REGULAR, "hello 33"),
                new Message(REGULAR, "hello 33"),
                null,
                new Message(MAJOR, "hello 33")
        );

        log(
                new Message("one message")
        );

        log(
                ASC,
                new Message(MAJOR, "with order"),
                new Message(MAJOR, "with order 2"),
                new Message(REGULAR, "with order 3"),
                new Message(MAJOR, "with order 4")
        );
    }
}
