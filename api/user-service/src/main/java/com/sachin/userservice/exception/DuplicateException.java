package com.sachin.userservice.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }
}
