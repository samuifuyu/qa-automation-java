package com.tcs.edu.decorator;

public class PostfixDecorator {
    public static String getPostfixSeverity(Severity severity) {
        return switch (severity) {
            case MINOR -> "()";
            case REGULAR -> "(!)";
            case MAJOR -> "(!!!)";
        };
    }
}
