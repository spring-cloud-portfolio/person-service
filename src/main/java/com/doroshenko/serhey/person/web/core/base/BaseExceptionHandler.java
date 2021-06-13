package com.doroshenko.serhey.person.web.core.base;

import com.doroshenko.serhey.person.dto.shared.web.ApiErrorResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Base exception handler, designed to be extended by concrete exception handlers
 *
 * @author Serhey Doroshenko
 * @see ResponseEntityExceptionHandler
 */
public abstract class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    protected WebRequest request;

    protected ResponseEntity<Object> handleException(final Exception e, final HttpStatus status) {
        logger.error(e.getMessage(), e);
        return handleExceptionInternal(e, extractBody(e, status), HttpHeaders.EMPTY, status, request);
    }

    protected ApiErrorResponseBody extractBody(final Exception e, final HttpStatus status) {
        return ApiErrorResponseBody.withStatus(status.value())
                .message(e.getMessage())
                .trace(printStackTrace(e))
                .error(status.getReasonPhrase())
                .path(request.getDescription(false))
                .build();
    }

    protected ResponseEntity<Object> handleException(final Exception e, final HttpStatus status, final HttpHeaders headers, final WebRequest request) {
        logger.error(e.getMessage(), e);
        return handleExceptionInternal(e, extractBody(e, status), headers, status, request);
    }

    /* Private methods */
    protected String printStackTrace(final Exception e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}
