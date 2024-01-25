package com.sachin.hotelservice.exception;

public class InValidDataException extends RuntimeException {
    public InValidDataException() {
        super();
    }

    public InValidDataException(String message) {
        super(message);
    }
}
