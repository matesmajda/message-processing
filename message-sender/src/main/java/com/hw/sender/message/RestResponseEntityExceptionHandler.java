package com.hw.sender.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    private static class Error {
        private String message;
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    protected ResponseEntity<Error> handleClientErrors(Exception ex) {
        return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Error> handleUnknownException(RuntimeException ex, WebRequest request) {
        log.error("Unknown exception. Path: {} Params: {} Exception: {}", request.toString(), request.getParameterMap().entrySet(), ex);
        return new ResponseEntity<>(new Error("Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}