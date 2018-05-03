package com.devin.rxjava_retrofit.http.result;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/25.
 */

public class HttpRespException extends RuntimeException {

    private int httpStatus;
    private int code;

    public HttpRespException(String message, int httpStatus, int code) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
