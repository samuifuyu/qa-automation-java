package com.tcs.edu.printer;

/**
 * This class is used to print out a given message.
 *
 * @author pepe
 */
public class ConsolePrinter implements Printer {
    /**
     * This method is used to print out a given message.
     *
     * @param message Message to print
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * This method is used to print a message with postfix
     *
     * @param message Message to print
     * @param postfix Postfix after message
     */
    @Override
    public void print(String message, String postfix) {
        System.out.printf("%s %s%n", message, postfix);
    }
}
