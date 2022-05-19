package com.tcs.edu.service.validation;

import java.util.Arrays;
import java.util.Objects;

public abstract class ValidatedService {
    protected boolean isArgsValid(Object... objects) {
        if (objects == null) {
            throw new IllegalArgumentException("objects is null");
        } else if (Arrays.stream(objects).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("some objects are null");
        } else if (Arrays.stream(objects).anyMatch(o -> o.toString().isEmpty())) {
            throw new IllegalArgumentException("some objects are empty");
        } else return true;
    }
}
