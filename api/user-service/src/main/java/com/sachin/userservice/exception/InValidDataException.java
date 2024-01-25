package com.sachin.userservice.exception;

public class InValidDataException extends RuntimeException {
    public InValidDataException() {
        super();
    }

    public InValidDataException(String message) {
        super(message);
    }
}
