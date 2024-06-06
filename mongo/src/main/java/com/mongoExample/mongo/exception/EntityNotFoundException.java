package com.mongoExample.mongo.exception;

public class EntityNotFoundException extends ServiceException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
