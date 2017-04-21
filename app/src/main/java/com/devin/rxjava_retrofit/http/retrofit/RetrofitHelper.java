package com.devin.rxjava_retrofit.http.retrofit;

import com.devin.rxjava_retrofit.http.okhttp.OkHttpHelper;
import com.devin.rxjava_retrofit.http.retrofit.converter.string.StringConverterFactory;
import com.devin.rxjava_retrofit.http.service.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/24.
 */

public class RetrofitHelper {
    private static Retrofit retrofit;


    private RetrofitHelper() {
    }

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(OkHttpHelper.getClient())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .validateEagerly(true)
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
