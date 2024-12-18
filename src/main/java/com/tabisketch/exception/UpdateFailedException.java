package com.tabisketch.exception;

public class UpdateFailedException extends RuntimeException{
    public UpdateFailedException(final String message){
        super(message);
    }
}
