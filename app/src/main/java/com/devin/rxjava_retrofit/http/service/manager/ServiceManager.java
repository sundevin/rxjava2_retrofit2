package com.devin.rxjava_retrofit.http.service.manager;


import com.devin.rxjava_retrofit.http.retrofit.RetrofitHelper;
import com.devin.rxjava_retrofit.http.service.ApiService;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/29.
 */

public class ServiceManager {

    public static ApiService getApiService() {
        return RetrofitHelper.getRetrofit().create(ApiService.class);
    }


    public static <T> T getService(Class<T> service) {
        return RetrofitHelper.getRetrofit().create(service);
    }


}
