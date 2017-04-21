package com.devin.rxjava_retrofit.util;

import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    public static boolean log_enabled = true;
    private static final String DEFAULT_TAG = "----------";

    private static final int DEBUG = 1;
    private static final int ERROR = 2;

    @IntDef({DEBUG, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    private @interface LogPriority {
    }

    private static final int MSG_MAX_LENGTH = 800;



    public static void d(String msg) {
        d(DEFAULT_TAG, msg);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }


    public static void d(String tag, String msg, Throwable tr) {
        printLog(tag,msg,tr,DEBUG);
    }


    public static void e(String msg) {
        e(DEFAULT_TAG, msg);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        printLog(tag, msg, tr, ERROR);
    }

    /**
     * 打印最终的日志
     *
     * @param tag         tag
     * @param msg         msg
     * @param tr          Throwable
     * @param logPriority logPriority
     */
    private static void printLog(String tag, String msg, Throwable tr, @LogPriority int logPriority) {
        if (!log_enabled) return;
        if (logPriority == DEBUG) {
            Log.d(tag, "__________________________________");
        } else {
            Log.e(tag, "__________________________________");
        }
        printLogLocation(tag);

        for (String str : splitLargeMsg(msg)) {
            if (logPriority == DEBUG) {
                Log.d(tag, str+"\t");
            } else {
                Log.e(tag, str+"\t");
            }
        }

        if (tr != null) {
            if (logPriority == DEBUG) {
                Log.d(tag, "", tr);
            } else {
                Log.e(tag, "", tr);
            }
        }

        if (logPriority == DEBUG) {
            Log.d(tag, "====================================");
        } else {
            Log.e(tag, "====================================");
        }
    }


    /**
     * 将比较长的msg 分割为 string list
     *
     * @param msg msg
     * @return string list
     */
    private static List<String> splitLargeMsg(String msg) {
        List<String> stringList = new ArrayList<>();
        int msgLength = msg.length();
        int beginIndex = 0;
        while (beginIndex < msgLength) {
            int endIndex = Math.min(msgLength, beginIndex + MSG_MAX_LENGTH);
            stringList.add(msg.substring(beginIndex, endIndex));
            beginIndex = endIndex;
        }
        return stringList;
    }



    /**
     * 打印log 的调用地址
     *
     * @param tag tag
     */
    private static void printLogLocation(String tag) {
        StackTraceElement stackTraceElement = getTargetStackTraceElement();
        Log.e(tag, "log location →(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")");
    }


    /**
     * 因为我们的入口是Logger的方法，所以，我们直接遍历，Logger类相关的下一个非Logger类的栈帧信息就是具体调用的方法。
     *
     * @return StackTraceElement
     */
    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(Logger.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
}
