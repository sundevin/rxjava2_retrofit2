package com.devin.rxjava_retrofit.http.retrofit.converter.string;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * <p>Description:
 * <p>Created by Devin Sun on 2017/3/28.
 */

public final class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {

        try {
            return value.string();
        } finally {
            value.close();
        }


    }
}
