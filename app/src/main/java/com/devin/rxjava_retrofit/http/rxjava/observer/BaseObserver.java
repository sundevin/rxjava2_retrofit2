package com.devin.rxjava_retrofit.http.rxjava.observer;


import com.devin.rxjava_retrofit.http.result.HttpRespException;
import com.devin.rxjava_retrofit.http.result.HttpRespResult;
import com.devin.rxjava_retrofit.http.result.HttpRespStatus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * <p>Description:
 * Created by Devin Sun on 2017/4/3.
 */
public class BaseObserver<T> implements Observer<HttpRespResult<T>> {

    HttpRespException responseException;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpRespResult<T> tHttpRespResult) {

    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            responseException = new HttpRespException(HttpRespStatus.DEFAULT_ERROR_MSG, httpException.code(), HttpRespStatus.DEFAULT_ERROR_CODE);
        } else if (e instanceof HttpRespException) {
            responseException = (HttpRespException) e;
        } else {//其他或者没网会走这里
            responseException = new HttpRespException(HttpRespStatus.NETWORK_ERROR_MSG, HttpRespStatus.NETWORK_ERROR, HttpRespStatus.DEFAULT_ERROR_CODE);
        }

    }

    @Override
    public void onComplete() {

    }


}
