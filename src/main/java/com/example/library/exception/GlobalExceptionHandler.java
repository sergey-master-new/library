package com.example.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<IncorrectData> handleException(
            NotFoundException exception) {
        IncorrectData incorrectData = incorrectDataFilling(exception);
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDeletionException.class)
    public ResponseEntity<IncorrectData> handleException(
            NoDeletionException exception) {
        IncorrectData incorrectData = incorrectDataFilling(exception);
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateFormatException.class)
    public ResponseEntity<IncorrectData> handleException(
            DateFormatException exception) {
        IncorrectData incorrectData = incorrectDataFilling(exception);
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    private IncorrectData incorrectDataFilling(Exception exception) {
        IncorrectData incorrectData = new IncorrectData();
        log.error("Message: {} Error UUID code: {}", exception.getMessage(), incorrectData.getErrorCode());
        exception.printStackTrace();
        incorrectData.setMessage(exception.getMessage());
        return incorrectData;
    }
}