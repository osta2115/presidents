package com.presidents.exception;

import com.presidents.exception.exceptions.EntityNotFoundExceptionOurs;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return new ResponseEntity<>(getBody(HttpStatus.BAD_REQUEST, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundExceptionOurs.class})
    public final ResponseEntity<Object> handleEntityNotFoundExceptionOurs(Exception ex) {
        return new ResponseEntity<>(getBody(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String message = Objects.nonNull(ex.getMessage()) ? ex.getMessage().split(":")[0] : ex.getMessage();
        return new ResponseEntity<>(getBody(HttpStatus.BAD_REQUEST, message), HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public final ResponseEntity<Object> handleHttpMessageNotReadableException(Exception ex) {
//        return new ResponseEntity<>(getBody(HttpStatus.BAD_REQUEST, ex.getMessage().split(":")[0]),
//                HttpStatus.BAD_REQUEST);
//    }

    private static Map<String, Object> getBody(HttpStatus status, Object message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error",status);
        body.put("message", message);
        return body;
    }
}
