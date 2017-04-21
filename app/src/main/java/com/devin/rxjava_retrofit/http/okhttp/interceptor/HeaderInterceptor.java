package com.devin.rxjava_retrofit.http.okhttp.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>Description:
 * <p>Created by Devin Sun on 2017/3/29.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .addHeader("yx", "")
                .addHeader("Authorization",
                        "Token a327153bb6a854f244a05ccf5025a2266257a0f8")
                .build();

        return chain.proceed(request);
    }
}
