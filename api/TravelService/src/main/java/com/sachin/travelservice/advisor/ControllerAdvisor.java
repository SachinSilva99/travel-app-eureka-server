package com.sachin.travelservice.advisor;


import com.sachin.travelservice.exception.DuplicateException;
import com.sachin.travelservice.exception.InValidDataException;
import com.sachin.travelservice.exception.NotFoundException;
import com.sachin.travelservice.util.StandardResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponse<String>> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<StandardResponse<String>> handleDuplicationException(DuplicateException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getMessage(), null
                ),
                HttpStatus.CONFLICT);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InValidDataException.class)
    public ResponseEntity<StandardResponse<String>> handleInValidDataException(InValidDataException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardResponse<String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getLocalizedMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse<String>> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        404, e.getMessage(), null
                ),
                HttpStatus.NOT_FOUND);
    }
}