package com.tcs.edu;

import com.tcs.edu.decorator.PostfixDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctMessageService;

import java.util.HashSet;

import static com.tcs.edu.service.Doubling.DISTINCT;
import static com.tcs.edu.service.MessageOrder.ASC;
import static com.tcs.edu.service.MessageOrder.DESC;
import static com.tcs.edu.service.Severity.*;

class Application {
    public static void main(String[] args) {

        HashMapMessageRepository repository = new HashMapMessageRepository();

        MessageService service = new OrderedDistinctMessageService(repository);

        Message message = new Message("message");
        service.log(message);

        System.out.println(repository.findByPrimaryKey(message.getId()));
        // f4125c33-b369-4426-be0f-3ad9021d0d1e: Message{body='message', severity=MINOR}

        System.out.println("\n--------------------\n");

        service.log(
                DISTINCT,
                new Message(MAJOR, "hello"),
                new Message(MAJOR, "a"),
                new Message(MAJOR, "b"),
                new Message(MINOR, "c"),
                new Message(MAJOR, "a")
        );
        /*
            83806246-127f-43e2-8881-84b11ef30960: Message{body='b', severity=MAJOR}
            c9205aa1-c8b6-45e0-9cbe-2acfcfae6d51: Message{body='hello', severity=MAJOR}
            a4b50651-c539-4dd1-852e-52100b5030e8: Message{body='a', severity=MAJOR}
            f4125c33-b369-4426-be0f-3ad9021d0d1e: Message{body='message', severity=MINOR}
            7aaf1c00-01a6-4dca-a44c-f8bdc607f479: Message{body='c', severity=MINOR}
         */

        repository.findAll().forEach(System.out::println);

        System.out.println("\n--------------------\n");

        repository.findBySeverity(MAJOR).forEach(System.out::println);
        /*
            83806246-127f-43e2-8881-84b11ef30960: Message{body='b', severity=MAJOR}
            c9205aa1-c8b6-45e0-9cbe-2acfcfae6d51: Message{body='hello', severity=MAJOR}
            a4b50651-c539-4dd1-852e-52100b5030e8: Message{body='a', severity=MAJOR}
         */
    }
}
