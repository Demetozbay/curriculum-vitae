package com.demet.curriculumvitae.exception.handler;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandler {
    /**
     * Our custom exceptions
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBaseException(WebRequest webRequest,
                                                 BaseException exception) {
        //exception.printStackTrace();
        return ResponseEntity
                .status(exception.status)
                .body(Map.of(
                        "status", exception.status,
                        "timestamp", System.currentTimeMillis(),
                        "error", Map.of(
                                "reason", exception.reason,
                                "parameter", exception.parameter,
                                "cause", exception.cause
                        )));
    }

    /**
     * If request body wrong type
     * (Like application/x-www-form-urlencoded)
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(WebRequest webRequest,
                                                                      HttpMediaTypeNotSupportedException exception) {
        //exception.printStackTrace();
        return ResponseEntity
                .status(415)
                .body(Map.of(
                        "status", 415,
                        "timestamp", System.currentTimeMillis(),
                        "error", Map.of(
                                "reason", "MediaTypeNotSupported",
                                "cause", "Unsupported media type."
                        )));
    }

    /**
     * If request body not contains required variable or wrong type
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(WebRequest webRequest,
                                                                   HttpMessageNotReadableException exception) {
        //exception.printStackTrace();
        return ResponseEntity
                .status(400)
                .body(Map.of(
                        "status", 400,
                        "timestamp", System.currentTimeMillis(),
                        "error", Map.of(
                                "reason", "NotReadable",
                                "cause",
                                "The request failed because it contained an invalid parameter or parameter value."
                        )));
    }

    /**
     * If request type wrong
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(WebRequest webRequest,
                                                                          HttpRequestMethodNotSupportedException exception) {
        //exception.printStackTrace();
        return ResponseEntity
                .status(400)
                .body(Map.of(
                        "status", 400,
                        "timestamp", System.currentTimeMillis(),
                        "error", Map.of(
                                "reason", "MethodNotSupported",
                                "cause", "The HTTP method associated with the request is not supported."
                        )));
    }

    /**
     * If request body couldn't validated
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        //exception.printStackTrace();
        return ResponseEntity
                .status(400)
                .body(Map.of(
                        "status", 400,
                        "timestamp", System.currentTimeMillis(),
                        "error", exception.getConstraintViolations()
                                          .stream()
                                          .map(error -> Map.of(
                                                  "reason", error.getPropertyPath()
                                                                 .toString(),
                                                  "cause", error.getMessage()
                                          ))
                                          .collect(Collectors.toList())
                ));
    }

    /**
     * If request body couldn't validated
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        //exception.printStackTrace();
        return ResponseEntity
                .status(400)
                .body(Map.of(
                        "status", 400,
                        "timestamp", System.currentTimeMillis(),
                        "error", exception.getBindingResult()
                                          .getFieldErrors()
                                          .stream()
                                          .map(error -> Map.of(
                                                  "reason", error.getField(),
                                                  "cause", Objects.requireNonNull(error.getDefaultMessage())
                                          ))
                                          .collect(Collectors.toList())
                ));
    }

    /**
     * If request path variable in wrong type
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(WebRequest webRequest,
                                                         TypeMismatchException exception) {
        //exception.printStackTrace();
        return ResponseEntity
                .status(400)
                .body(Map.of(
                        "status", 400,
                        "timestamp", System.currentTimeMillis(),
                        "error", Map.of(
                                "reason", "Mismatch",
                                "cause", "The request not contains required path parameter or parameters."
                        )));
    }
}
