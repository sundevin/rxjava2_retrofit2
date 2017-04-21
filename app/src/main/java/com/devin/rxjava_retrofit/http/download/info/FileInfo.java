package com.devin.rxjava_retrofit.http.download.info;

/**
 * Description:
 * Company:
 * Email:bjxm2013@163.com
 * Created by Devin Sun on 2017/4/3.
 */
public class FileInfo {
    public String fileName;
    public String parentPath;
    public long fileTotalSize;
    public long fileCurrentSize;
    public boolean done;

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", parentPath='" + parentPath + '\'' +
                ", fileTotalSize=" + fileTotalSize +
                ", fileCurrentSize=" + fileCurrentSize +
                ", done=" + done +
                '}';
    }
}
