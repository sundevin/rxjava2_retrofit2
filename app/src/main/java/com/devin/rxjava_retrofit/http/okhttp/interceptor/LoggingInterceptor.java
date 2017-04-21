package com.devin.rxjava_retrofit.http.okhttp.interceptor;

import java.io.IOException;
import java.net.URLDecoder;

import com.devin.rxjava_retrofit.util.Logger;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Description:
 * Company:
 * Email:bjxm2013@163.com
 * Created by Devin Sun on 2017/3/31.
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        printlnRequestInfo(request);

        Response response = chain.proceed(request);

        return  printlnResponseInfo(response);
    }

    /**
     * 打印Request info
     *
     * @param request
     */
    private void printlnRequestInfo(Request request) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("======request's log begin======\n");

        String url = request.url().toString();
        stringBuilder.append("url: ").append(url).append("\n");
        stringBuilder.append("method: ").append(request.method()).append("\n");

        Headers headers = request.headers();
        if (headers != null) {
            stringBuilder.append("headers: ").append("\n").append(headers.toString()).append("\n");
        } else {
            stringBuilder.append("headers:headers is null !\n");
        }

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            MediaType mediaType = requestBody.contentType();
            if (mediaType != null) {
                stringBuilder.append("request mediaType: ").append(mediaType.toString()).append("\n");
                if (isTextMediaType(mediaType)) {
                    stringBuilder.append("requestBody's content: \n").append(requestBodyToStr(request)).append("\n");
                } else {
                    stringBuilder.append("requestBody's content : maybe [file part], ignored print !\n");
                }
            } else {
                stringBuilder.append("request mediaType:mediaType is null !\n");
            }
        } else {
            stringBuilder.append("requestBody:requestBody is null !\n");
        }
        stringBuilder.append("======request's log end======");
        Logger.d(stringBuilder.toString());
    }

    /**
     * print Response info
     * @param response
     * @return newResponse
     */
    private Response printlnResponseInfo(Response response) {

        Response newResponse=response;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("======response's log begin======\n");

        String url = response.request().url().toString();
        stringBuilder.append("url: ").append(url).append("\n");
        stringBuilder.append("method: ").append(response.request().method()).append("\n");
        stringBuilder.append("protocol: ").append(response.protocol()).append("\n");
        stringBuilder.append("code: ").append(response.code()).append("\n");
        stringBuilder.append("message: ").append(response.message()).append("\n");

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            MediaType mediaType = responseBody.contentType();
            if (mediaType != null) {
                stringBuilder.append("response mediaType: ").append(mediaType.toString()).append("\n");
                if (isTextMediaType(mediaType)) {
                    try {
                        String str=responseBody.string();
                        stringBuilder.append("responseBody's  content: \n").append(str).append("\n");
                        newResponse=response.newBuilder().body(ResponseBody.create(mediaType,str)).build();
                    } catch (IOException e) {
                        stringBuilder.append("responseBody's content: print error !!! ").append(e.getMessage()).append("\n");
                    }
                } else {
                    stringBuilder.append("responseBody's content: maybe [file part] , ignored print !\n");
                }
            } else {
                stringBuilder.append("response mediaType:　mediaType is null !\n");
            }
        } else {
            stringBuilder.append("responseBody: responseBody is null !\n");
        }

        stringBuilder.append("======response's log end======");
        Logger.d(stringBuilder.toString());

        return newResponse;
    }


    private boolean isTextMediaType(MediaType mediaType) {
        if (mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json")
                    || mediaType.subtype().equals("xml")
                    || mediaType.subtype().equals("html")
                    || mediaType.subtype().equals("x-www-form-urlencoded")
                    || mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }


    private String requestBodyToStr(Request request) {

        try {
            Request copy = request.newBuilder().build();
            Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return URLDecoder.decode(buffer.readUtf8(),"utf-8");
        } catch (final IOException e) {
            return "something error when show requestBody:" + e.getMessage();
        }

    }


}
