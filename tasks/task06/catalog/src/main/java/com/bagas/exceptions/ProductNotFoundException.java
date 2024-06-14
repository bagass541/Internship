package com.bagas.exceptions;

public class ProductNotFoundException extends NullPointerException {

    public ProductNotFoundException(String s) {
        super(s);
    }
}
