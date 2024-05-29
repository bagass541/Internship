package com.bagas.util;

import com.bagas.exceptions.ParameterNullException;

import java.util.Objects;

import static com.bagas.constants.ExceptionMessageConstants.PARAMETER_IS_NULL_MESSAGE;

public class ParameterChecker {

    public static <T> T checkParameter(T object) {
        if(Objects.isNull(object)) {
            throw new ParameterNullException(PARAMETER_IS_NULL_MESSAGE);
        }

        return object;
    }
}
