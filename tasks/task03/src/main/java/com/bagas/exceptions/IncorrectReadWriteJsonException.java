package com.bagas.exceptions;

import java.io.IOException;

public class IncorrectReadWriteJsonException extends IOException {

    public IncorrectReadWriteJsonException(String message) {
        super(message);
    }
}
