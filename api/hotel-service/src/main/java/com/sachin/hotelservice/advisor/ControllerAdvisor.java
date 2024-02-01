package com.sachin.hotelservice.advisor;

import com.sachin.hotelservice.exception.DuplicateException;
import com.sachin.hotelservice.exception.InValidDataException;
import com.sachin.hotelservice.exception.NotFoundException;
import com.sachin.hotelservice.util.StandardResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerAdvisor {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<StandardResponse> handleDuplicationException(DuplicateException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400, e.getMessage(), null
                ),
                HttpStatus.CONFLICT);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InValidDataException.class)
    public ResponseEntity<StandardResponse> handleInValidDataException(InValidDataException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400, e.getLocalizedMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleInValidDataException(NotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        404, e.getMessage(), null
                ),
                HttpStatus.NOT_FOUND);
    }
}