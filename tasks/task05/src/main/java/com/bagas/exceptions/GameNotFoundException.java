package com.bagas.exceptions;

public class GameNotFoundException extends ClassNotFoundException{

    public GameNotFoundException(String s) {
        super(s);
    }
}
