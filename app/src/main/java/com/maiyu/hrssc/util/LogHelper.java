/**
 * Created on Apr 9, 2012 5:26:27 PM
 */
package com.maiyu.hrssc.util;

/**
 * <p>Created on Apr 9, 2012 5:26:27 PM</p>
 * <p>Module: AppStore5</p>
 * <p>Package: com.lenovo.leos.appstore5.tool</p>
 * <p>File: Logger.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011. All rights reserved.</p>
 * <p>Company: www.lenovo.com</p>
 * @author <a href="mailto:qixl1@lenovo.com">漆兴隆(Seven Qi)</a>
 * @version 1.0
 * @see
 */

public class LogHelper {

    public static final int ALL = 1;

    public static final int VERBOSE = android.util.Log.VERBOSE;

    public static final int DEBUG = android.util.Log.DEBUG;

    public static final int INFO = android.util.Log.INFO;

    public static final int WARN = android.util.Log.WARN;

    public static final int ERROR = android.util.Log.ERROR;

    public static final int ASSERT = android.util.Log.ASSERT;

    private static final boolean isLog = true;

    private static int filter = VERBOSE;

    public static void setFilter(int level) {
        filter = level;
    }

    public static boolean isDebug() {
        if (isLog && filter <= DEBUG)
            return true;
        else
            return false;
    }

    public static boolean isLoggable(String tag, int level) {
        if (isLog && filter <= level)
            return true;
        else
            return false;
    }

    public static int e(String tag, String msg) {
        return (isLog && filter <= ERROR) ? android.util.Log.e(tag, msg) : 0;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return (isLog && filter <= ERROR) ? android.util.Log.e(tag, msg, tr) : 0;
    }

    public static int w(String tag, String msg) {
        return (isLog && filter <= WARN) ? android.util.Log.w(tag, msg) : 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return (isLog && filter <= WARN) ? android.util.Log.w(tag, msg, tr) : 0;
    }

    public static int i(String tag, String msg) {
        return (isLog && filter <= INFO) ? android.util.Log.i(tag, msg) : 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        return (isLog && filter <= INFO) ? android.util.Log.i(tag, msg, tr) : 0;
    }

    public static int d(String tag, String msg) {
        return (isLog && filter <= DEBUG) ? android.util.Log.d(tag, msg) : 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return (isLog && filter <= DEBUG) ? android.util.Log.d(tag, msg, tr) : 0;
    }

    public static int v(String tag, String msg) {
        return (isLog && filter <= VERBOSE) ? android.util.Log.v(tag, msg) : 0;
    }

    public static int v(String tag, String msg, Throwable tr) {
        return (isLog && filter <= VERBOSE) ? android.util.Log.v(tag, msg, tr) : 0;
    }

    public static void getTraces(String tag) {
        LogHelper.w(tag, tag, new Exception(tag));
    }

    /**
     * 打印程序调用的Task信息
     * @param str
     */
    public static void printStackTrace(String str) {
        StackTraceElement st[] = Thread.currentThread().getStackTrace();
        for (int i = 0; i < st.length; i++) {
            LogHelper.d(str, i + ":" + st[i]);
        }
    }

    /**
     * 打印程序调用的Task信息
     * @param str
     * @param index  StackTrace起始位置
     */
    public static void printStackTrace(String str, int index) {
        StackTraceElement st[] = Thread.currentThread().getStackTrace();
        if (index < st.length) {
            for (int i = index; i < st.length; i++) {
                LogHelper.d(str, i + ":" + st[i]);
            }
        } else {
            LogHelper.d(str, "index invalid");
        }
    }

    /**
     * 打印程序调用的Task信息
     * @param str
     * @param begin  StackTrace起始位置
     * @param end  StackTrace结束位置
     */
    public static void printStackTrace(String str, int begin, int end) {
        StackTraceElement st[] = Thread.currentThread().getStackTrace();
        if (begin < st.length) {
            end = end < st.length ? ++end : st.length;
            for (int i = begin; i < end; i++) {
                LogHelper.d(str, i + ":" + st[i]);
            }
        } else {
            LogHelper.d(str, "index invalid");
        }
    }
}
