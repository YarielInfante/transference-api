package com.revolut.transference.web.dto;

import java.io.Serializable;

public class HttpResponseError implements Serializable {

    private ErrorCode errorCode;
    private String message;

    public HttpResponseError() {
    }

    public HttpResponseError(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
