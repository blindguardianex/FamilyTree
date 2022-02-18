package org.brutforcer.service.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return processException(MessageFormat.format("Недопустимый аргумент: {1}", exception.getMessage()),
                exception, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<Map<String,String>> processException(String message, Exception exception, HttpStatus status) {
        return processException(message, null, exception, status);
    }

    private ResponseEntity<Map<String,String>> processException(String message, String detailedMessage, Exception exception, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        log.error(message, exception);
        Map<String,String> result = new HashMap<>();
        result.put("Exception", exception.getClass().getName());
        result.put("Error class name", getErrorClassName(exception));
        result.put("Message", message);
        if (StringUtils.isNotEmpty(detailedMessage)) {
            result.put("Detailed message", detailedMessage);
        }

        return new ResponseEntity<>(
                result,
                headers,
                status
        );
    }

    private String getErrorClassName(Exception exception) {
        return exception.getStackTrace().length>0 ?
                exception.getStackTrace()[0].getClassName() : AccessDeniedException.class.getName();
    }
}
