package com.sachin.travelservice.exception;

public class UpdateNotAllowedException extends RuntimeException {
    public UpdateNotAllowedException() {
        super();
    }

    public UpdateNotAllowedException(String message) {
        super(message);
    }
}
