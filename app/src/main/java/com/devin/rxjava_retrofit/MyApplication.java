package com.devin.rxjava_retrofit;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * <p>Description:
 *
 * <p>Created by Devin Sun on 2017/3/28.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    public  static  MyApplication getInstance(){
        return myApplication;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        myApplication= this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }


}
