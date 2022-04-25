package com.tcs.edu;

import static com.tcs.edu.Doubling.DISTINCT;
import static com.tcs.edu.Doubling.DOUBLES;
import static com.tcs.edu.MessageOrder.ASC;
import static com.tcs.edu.MessageOrder.DESC;
import static com.tcs.edu.MessageService.process;
import static com.tcs.edu.decorator.Severity.MAJOR;

class Application {
    public static void main(String[] args) {

        process(MAJOR, DESC, DOUBLES, "hello","hello 1", "hello 3", "hello 2", "hello 2", null, "hello 5");

        process(MAJOR, ASC, DISTINCT, "bye", "bye 3", "bye 9", "bye 5", "bye 5", null, "bye 1");
    }
}
