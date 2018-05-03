package com.devin.rxjava_retrofit.http.rxjava.observable;

import com.devin.rxjava_retrofit.http.result.HttpRespException;
import com.devin.rxjava_retrofit.http.result.HttpRespResult;
import com.devin.rxjava_retrofit.http.result.HttpRespStatus;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/29.
 */

public class TransformerHelper {


    public static <T> ObservableTransformer<HttpRespResult<T>, HttpRespResult<T>> transformer() {
        return new ObservableTransformer<HttpRespResult<T>, HttpRespResult<T>>() {
            @Override
            public ObservableSource<HttpRespResult<T>> apply(Observable<HttpRespResult<T>> upstream) {
                return upstream.concatMap(TransformerHelper.<T>checkCode())
                        .compose(TransformerHelper.<HttpRespResult<T>>schedulerTransf());
            }
        };
    }

    /**
     * 根据HttpRespResult对象的code 判断是否为业务成功
     *
     * @param <T>
     * @return
     */
    public static <T> Function<HttpRespResult<T>, ObservableSource<? extends HttpRespResult<T>>> checkCode() {
        return new Function<HttpRespResult<T>, ObservableSource<? extends HttpRespResult<T>>>() {
            @Override
            public ObservableSource<? extends HttpRespResult<T>> apply(HttpRespResult<T> tHttpRespResult) throws Exception {
                if (tHttpRespResult.isSuccess()) {
                    return Observable.just(tHttpRespResult);
                } else {
                    return Observable.error(new HttpRespException(tHttpRespResult.getMsg(), HttpRespStatus.HTTP_RESP_SUCCESS_CODE, tHttpRespResult.getState()));
                }
            }
        };
    }

    /**
     * 切换线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> schedulerTransf() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



}
