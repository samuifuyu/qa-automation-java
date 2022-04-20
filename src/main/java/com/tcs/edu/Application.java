package com.tcs.edu;

import com.tcs.edu.decorator.Severity;

import java.util.Random;

import static com.tcs.edu.MessageService.process;

class Application {
    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            Severity severity = Severity.values()[new Random().nextInt(Severity.values().length)];
            String message = "Example 1";

            process(severity, message);
        }

        for (int i = 0; i < 2; i++) {
            Severity severity = Severity.values()[new Random().nextInt(Severity.values().length)];
            String message = "Example 2";
            String anotherMessage = "Additional message here";
            String nullMessage = null;

            process(severity, message, anotherMessage, nullMessage);
        }
    }
}
