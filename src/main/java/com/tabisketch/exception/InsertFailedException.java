package com.tabisketch.exception;

public class InsertFailedException extends RuntimeException{
    public InsertFailedException(final String message){
        super(message);
    }
}
