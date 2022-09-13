package com.sametcanal.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AtmBusinessException extends RuntimeException{
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    public AtmBusinessException(){}

    public AtmBusinessException(final String errorCode, final String errorMessage, final HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

}
