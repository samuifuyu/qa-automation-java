package com.tcs.edu;

import com.tcs.edu.decorator.PostfixDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctMessageService;

import static com.tcs.edu.service.Doubling.DISTINCT;
import static com.tcs.edu.service.MessageOrder.ASC;
import static com.tcs.edu.service.MessageOrder.DESC;
import static com.tcs.edu.service.Severity.*;

class Application {
    public static void main(String[] args) {

        MessageService service  = new OrderedDistinctMessageService(
                new ConsolePrinter(),
                new TimestampMessageDecorator(),
                new PostfixDecorator()
        );

        service.log(
                new Message("simple")
        );

        service.log(
                ASC,
                DISTINCT,
                new Message(MAJOR, "ordered distinct"),
                new Message(MAJOR, "b"),
                new Message(MINOR, "c"),
                new Message(REGULAR, "a"),
                new Message(REGULAR, "f"),
                new Message(REGULAR, "d"),
                new Message(REGULAR, "e"),
                new Message(REGULAR, "d"),
                null,
                new Message(MAJOR, "d")
        );

        service.log(
                DESC,
                new Message(MAJOR, "ordered"),
                new Message(MAJOR, "b"),
                new Message(MINOR, "c"),
                new Message(REGULAR, "a"),
                new Message(REGULAR, "d"),
                new Message(REGULAR, "d"),
                null,
                new Message(MAJOR, "d")
        );
    }
}
