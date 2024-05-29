package com.bagas.exceptions;

public class UserNotFoundException extends ClassNotFoundException {

    public UserNotFoundException(String s) {
        super(s);
    }
}
