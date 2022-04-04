package com.tcs.edu;

import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.printer.ConsolePrinter;

class Application {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ConsolePrinter.print(TimestampMessageDecorator.decorate("kek"));
        }
    }
}
