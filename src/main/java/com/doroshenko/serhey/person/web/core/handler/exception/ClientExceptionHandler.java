package com.doroshenko.serhey.person.web.core.handler.exception;

import com.doroshenko.serhey.person.web.core.base.BaseExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Designed to handle errors where the cause is the client
 *
 * @author Serhey Doroshenko
 * @see BaseExceptionHandler
 */
@RestControllerAdvice
public class ClientExceptionHandler extends BaseExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handle(final AccessDeniedException e) {
        return handleException(e, HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handle(final EntityNotFoundException e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handle(final ConstraintViolationException e) {
        final String messages = e.getConstraintViolations().stream()
                .map(cv -> "Message: " + cv.getMessage() + " Path: " + cv.getPropertyPath())
                .collect(Collectors.joining(", "));
        return handleException(new ConstraintViolationException(messages, e.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleException(ex, status, headers, request);
    }

}
