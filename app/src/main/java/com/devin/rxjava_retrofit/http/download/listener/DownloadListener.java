package com.devin.rxjava_retrofit.http.download.listener;

import java.io.File;

/**
 * Description:
 * Company:
 * Email:bjxm2013@163.com
 * Created by Devin Sun on 2017/4/3.
 */
public interface DownloadListener {

    void update(long bytesRead, long contentLength);

    void onSuccess(File file);

    void onFailure(Throwable t);
}
