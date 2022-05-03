package com.tcs.edu.decorator;

import com.tcs.edu.service.Severity;

public class PostfixDecorator implements MessageDecorator<Severity> {

    @Override
    public String decorate(Severity message) {
        return switch (message) {
            case MINOR -> "()";
            case REGULAR -> "(!)";
            case MAJOR -> "(!!!)";
        };
    }
}
