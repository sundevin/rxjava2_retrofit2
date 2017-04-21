package com.devin.rxjava_retrofit.util;

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.devin.rxjava_retrofit.MyApplication;


public class ToastUtils {

    private static Toast toast;

    public static void show(String text) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(), "", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.setText(text);
        toast.show();
    }

    public static void show(@StringRes int resId) {
        show(MyApplication.getInstance().getResources().getString(resId));
    }

}
