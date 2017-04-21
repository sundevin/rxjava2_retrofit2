package com.devin.rxjava_retrofit.http.rxjava.observer;

import android.support.annotation.CallSuper;

import com.devin.rxjava_retrofit.http.result.HttpResponseException;
import com.devin.rxjava_retrofit.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/29.
 */

public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {

        HttpResponseException responseException;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            responseException = new HttpResponseException("网络请求出错", httpException.code());
        } else if (e instanceof HttpResponseException) {
            responseException = (HttpResponseException) e;
        } else {//其他或者没网会走这里
            responseException = new HttpResponseException("网络异常,请稍后重试", -1024);
        }

        onFailed(responseException);
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onSuccess(T t);

    @CallSuper
    protected void onFailed(HttpResponseException responseException) {
        ToastUtils.show(responseException.getMessage() + "(" + responseException.getStatus() + ")");
    }
}
