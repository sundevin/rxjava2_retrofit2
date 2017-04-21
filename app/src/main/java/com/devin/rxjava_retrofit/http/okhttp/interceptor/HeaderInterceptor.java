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
//                .addHeader()
                // add header...
                .build();

        return chain.proceed(request);
    }
}
