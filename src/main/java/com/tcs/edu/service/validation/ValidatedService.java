package com.tcs.edu.service.validation;

import java.util.Arrays;
import java.util.Objects;

public abstract class ValidatedService {
    protected boolean isArgsValid(Object... objects) {
        if (objects == null) return false;
        else return Arrays.stream(objects).noneMatch(Objects::isNull);
    }
}
