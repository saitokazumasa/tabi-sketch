package com.tabisketch.exception;

public class DeleteFailedException extends RuntimeException{
    public DeleteFailedException(final String message){
        super(message);
    }
}
