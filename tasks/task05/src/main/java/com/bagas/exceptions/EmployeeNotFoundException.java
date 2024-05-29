package com.bagas.exceptions;

public class EmployeeNotFoundException extends ClassNotFoundException {

    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
