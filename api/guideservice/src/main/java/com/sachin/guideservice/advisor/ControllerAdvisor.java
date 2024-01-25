package com.sachin.guideservice.advisor;

import com.mongodb.MongoWriteException;

import com.sachin.guideservice.exception.DuplicateException;
import com.sachin.guideservice.exception.ImageFileException;
import com.sachin.guideservice.exception.InValidDataException;
import com.sachin.guideservice.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.sachin.guideservice.util.StandardResponse;

@ControllerAdvice
public class ControllerAdvisor {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponse<String>> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.CONFLICT)
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse<String>> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        404, e.getMessage(), null
                ),
                HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<StandardResponse<String>> handleMongoWriteException(MongoWriteException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageFileException.class)
    public ResponseEntity<StandardResponse<String>> handleImageFileException(ImageFileException e) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
}
