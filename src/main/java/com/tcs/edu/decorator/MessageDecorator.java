package com.tcs.edu.decorator;

public interface MessageDecorator<T> {

    String decorate(T message);
}
