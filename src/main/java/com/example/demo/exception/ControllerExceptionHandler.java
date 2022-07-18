package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ErrorResponse errors = ErrorResponse
                .builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errors = ErrorResponse
                .builder()
                .status(ErrorCode.INVALID_INPUT_VALUE.getStatus())
                .message(ErrorCode.INVALID_INPUT_VALUE.getMessage())
                .build();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        ErrorResponse response = ErrorResponse
                .builder()
                .status(e.getErrorCode().getStatus())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(response);
    }

}