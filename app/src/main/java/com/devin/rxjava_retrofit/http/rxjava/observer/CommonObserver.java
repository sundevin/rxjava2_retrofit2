package com.devin.rxjava_retrofit.http.rxjava.observer;

import com.devin.rxjava_retrofit.http.result.HttpRespException;
import com.devin.rxjava_retrofit.http.result.HttpRespResult;
import com.devin.rxjava_retrofit.util.ToastUtils;

/**
 * <p>Description:
 * Created by Devin Sun on 2017/4/3.
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {


    @Override
    public final void onNext(HttpRespResult<T> tHttpRespResult) {
        onSuccess(tHttpRespResult.getResult());
    }

    @Override
    public final void onError(Throwable e) {
        super.onError(e);
        onFailed(responseException);
    }

    public abstract void onSuccess(T t);

    public void onFailed(HttpRespException responseException) {
        ToastUtils.show(responseException.getMessage() + "(" + responseException.getHttpStatus() + "," + responseException.getCode() + ")");
    }
}
