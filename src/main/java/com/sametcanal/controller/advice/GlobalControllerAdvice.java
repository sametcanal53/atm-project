package com.sametcanal.controller.advice;

import com.sametcanal.exception.AtmBusinessExceptionObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sametcanal.exception.AtmBusinessException;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice{

    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";

    private static final String RETURN_CODE_HEADER = "atm-return-code";
    private static final String RETURN_MESSAGE_HEADER = "atm-return-message";

    @ExceptionHandler(AtmBusinessException.class)
    public ResponseEntity<Object> handle(AtmBusinessException e) {
        log.error(e.getMessage(), e);

        String errorMessage = e.getErrorMessage();
        String errorCode = e.getErrorCode();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        responseHeaders.add(RETURN_CODE_HEADER, errorCode);
        responseHeaders.add(RETURN_MESSAGE_HEADER, errorMessage);

        ErrorResponse responseBody = ErrorResponse.builder()
                .errorType(e.getClass().getName())
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        return ResponseEntity
                .status(e.getHttpStatus())
                .headers(responseHeaders)
                .body(responseBody);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> nestedObjectExceptionHandling(DataIntegrityViolationException exceptions){
        return new ResponseEntity<AtmBusinessExceptionObject>(new AtmBusinessExceptionObject("ATM-2000","Nested object exception",HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
        Map<String, String> errors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<Object>( new AtmBusinessExceptionObject("ATM-2001", errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}