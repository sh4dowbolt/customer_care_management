package com.suraev.exception;

import com.suraev.dto.Problem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionTranslator {

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @ExceptionHandler
    ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException exception) {

        return new ResponseEntity<>(createErrorResponse(HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    ResponseEntity<Problem> handleProductNotFoundException(BadRequestAlertException exception) {

        return new ResponseEntity<>(createErrorResponse(HttpStatus.NOT_FOUND),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    ResponseEntity<Problem> handleUserNotFoundException(BadRequestAlertException exception) {

        return new ResponseEntity<>(createErrorResponse(HttpStatus.NOT_FOUND),HttpStatus.BAD_REQUEST);
    }

    Problem createErrorResponse(HttpStatus status) {
        return Problem.builder().title(applicationName)
                .status(status.toString()).code(status.value()).generatedAt(Instant.now())
                .build();
    }
}
