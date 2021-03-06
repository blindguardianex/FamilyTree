package org.brutforcer.service.user.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.brutforcer.common.exceptions.EntityAlreadyExist;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @SneakyThrows
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Map<String,String>>  handleBindException(HttpServletRequest request, BindException exception) {
        var errorMap = processException(
                MessageFormat.format("Недопустимый аргумент: {0}", exception.getMessage()),
                exception
        );
        exception.getBindingResult().getAllErrors()
                .stream()
                .map(FieldError.class::cast)
                .forEach(error->errorMap.put(
                        error.getField(),
                        String.format("%s, введенные данные: %s", error.getDefaultMessage(), error.getRejectedValue())));
        return new ResponseEntity<>(
                errorMap,
                getErrorHttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(EntityAlreadyExist.class)
    public ResponseEntity<Map<String,String>> entityAlreadyExist(EntityAlreadyExist exception) {
        return new ResponseEntity<>(
                processException(
                        MessageFormat.format("Сущность уже существует: {0}", exception.getMessage()),
                        exception),
                getErrorHttpHeaders(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<Map<String,String>> psqlException(PSQLException exception) {
        return new ResponseEntity<>(
                processException(
                        MessageFormat.format("Ошибка добавления в БД: {0},\n{1}", exception.getMessage()),
                        exception),
                getErrorHttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new ResponseEntity<>(
                processException(
                        MessageFormat.format("Недопустимый аргумент: {0}", exception.getMessage()),
                        exception),
                getErrorHttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    private Map<String,String> processException(String message, Exception exception) {
        return processException(message, null, exception);
    }

    private Map<String,String> processException(String message, String detailedMessage, Exception exception) {
        log.error(message, exception);
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Exception", exception.getClass().getName());
        errorMap.put("Error class name", getErrorClassName(exception));
        errorMap.put("Message", message);
        if (StringUtils.isNotEmpty(detailedMessage)) {
            errorMap.put("Detailed message", detailedMessage);
        }

        return errorMap;
    }

    private HttpHeaders getErrorHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private String getErrorClassName(Exception exception) {
        return exception.getStackTrace().length>0 ?
                exception.getStackTrace()[0].getClassName() : AccessDeniedException.class.getName();
    }
}
