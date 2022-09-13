package com.sametcanal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AtmBusinessExceptionObject {
    private String errorCode;
    private Object errorMessage;
    private HttpStatus httpStatus;

    public AtmBusinessExceptionObject() {
    }

    public AtmBusinessExceptionObject(final String errCode, final Object errorMessage, final HttpStatus httpStatus) {
        this.errorCode = errCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
