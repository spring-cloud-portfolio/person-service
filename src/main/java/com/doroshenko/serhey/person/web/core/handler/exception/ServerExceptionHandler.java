package com.doroshenko.serhey.person.web.core.handler.exception;

import com.doroshenko.serhey.person.web.core.base.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Designed to handle errors where the cause is the server
 *
 * @author Serhey Doroshenko
 * @see BaseExceptionHandler
 */
@RestControllerAdvice
public class ServerExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handle(final Exception e) {
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
