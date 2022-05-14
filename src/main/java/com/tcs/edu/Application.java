package com.tcs.edu;

import com.tcs.edu.decorator.PostfixDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctMessageService;

import java.util.HashSet;

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

        try {
            service.log(
                    null,
                    null,
                    new Message(""),
                    null
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        }

        try {
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
                    new Message("r"),
                    new Message(MAJOR, "d")
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        }

        try {
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
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        }

        Message message = new Message(MINOR, "hello");
        Message sameMessage = new Message(MINOR, "hello");
        Message anotherMessage = new Message(MAJOR, "hello!");

        System.out.println(message);
        System.out.println(message.equals(anotherMessage));
        System.out.println(message.equals(sameMessage));
        System.out.println(message.hashCode());
        System.out.println(anotherMessage.hashCode());

        HashSet<Message> hashSet = new HashSet<>();
        hashSet.add(message);
        hashSet.add(sameMessage);
        hashSet.add(anotherMessage);
        System.out.println(hashSet.size());
    }
}
