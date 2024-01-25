package com.sachin.guideservice.exception;

public class InValidDataException extends RuntimeException {
    public InValidDataException() {
        super();
    }

    public InValidDataException(String message) {
        super(message);
    }
}
