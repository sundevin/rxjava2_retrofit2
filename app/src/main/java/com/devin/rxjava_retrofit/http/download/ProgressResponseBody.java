package com.devin.rxjava_retrofit.http.download;

import java.io.IOException;

import com.devin.rxjava_retrofit.http.download.listener.DownloadListener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Description:
 * Company:
 * Email:bjxm2013@163.com
 * Created by Devin Sun on 2017/4/3.
 */
public class ProgressResponseBody extends ResponseBody {

    private long contentLength ;

    private final ResponseBody responseBody;
    private final DownloadListener downloadListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, DownloadListener downloadListener) {
        this.responseBody = responseBody;
        this.downloadListener = downloadListener;
        contentLength=responseBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long currentTotalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                currentTotalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (downloadListener != null) {
                    Observable.just(currentTotalBytesRead)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(@NonNull Long aLong) throws Exception {
                                    downloadListener.update(currentTotalBytesRead, contentLength);
                                }
                            });

                }
                return bytesRead;
            }
        };
    }

}
