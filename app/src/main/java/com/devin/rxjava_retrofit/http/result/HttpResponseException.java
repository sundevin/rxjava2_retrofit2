package com.devin.rxjava_retrofit.http.result;

/**
 * <p>Description:
 *
 * <p>Created by Devin Sun on 2017/3/25.
 */

public class HttpResponseException extends RuntimeException {

    private  int status;

    public HttpResponseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
