package com.danwsaps.catalog.exception;

import com.danwsaps.catalog.dto.common.ErrorResponseDTO;
import com.danwsaps.catalog.enums.ResponseStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalHandlerException implements Serializable {

    @Serial
    private static final long serialVersionUID = -8079688678153128612L;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        return build(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        return build(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return build(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return build(ex, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ResponseEntity<ErrorResponseDTO> build(Exception ex, HttpStatus status) {
        String message;

        if (ex instanceof MethodArgumentNotValidException vex) {
            message = vex
                    .getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + " " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        } else {
            message = ex.getMessage();
        }

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .status(ResponseStatus.ERROR)
                .message(message)
                .build();

        log.error(message);

        return ResponseEntity
                .status(status)
                .body(error);
    }

}
