package com.tcs.edu.printer;

/**
 * This class is used to print out a given message.
 *
 * @author pepe
 */
public class ConsolePrinter {
    /**
     * This method is used to print out a given message.
     *
     * @param message Message to print
     */
    public static void print(String message, String postfix) {
        System.out.printf("%s %s%n", message, postfix);
    }

    public static void print(String message) {
        System.out.println(message);
    }
}
