package com.devin.rxjava_retrofit.entity;

import java.util.List;

/**
 * Description:
 * Company:
 * Email:bjxm2013@163.com
 * Created by Devin Sun on 2017/4/21.
 */
public class LogisticsInfo {

    private String message;
    private String id;
    private int num;
    private String order;
    private int status;
    private String updateTime;
    private String name;
    private int errCode;
    private List<DataBean> data;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getOrder() {
        return order;
    }

    public int getStatus() {
        return status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getName() {
        return name;
    }

    public int getErrCode() {
        return errCode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {


        private String content;
        private String time;

        public void setContent(String content) {
            this.content = content;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public String getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "content='" + content + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LogisticsInfo{" +
                "message='" + message + '\'' +
                ", id='" + id + '\'' +
                ", num=" + num +
                ", order='" + order + '\'' +
                ", status=" + status +
                ", updateTime='" + updateTime + '\'' +
                ", name='" + name + '\'' +
                ", errCode=" + errCode +
                ", data=" + data +
                '}';
    }
}
