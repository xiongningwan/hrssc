package com.maiyu.hrssc.util;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Stack;

public class ActivityManager {
    private static Stack<FragmentActivity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getScreenManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    //退出栈顶Activity 
    public void popActivity(FragmentActivity activity) {
        if (activity != null) {
            //在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作 
            activity.finish();
            activityStack.remove(activity);
            Log.i("ActivityManager", "pop:"+activity.getClass().getName());
            activity = null;
        }
    }

    //获得当前栈顶Activity 
    public FragmentActivity currentActivity() {
        FragmentActivity activity = null;
        if (!activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }

    //将当前Activity推入栈中 
    public void pushActivity(FragmentActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<FragmentActivity>();
        }
        activityStack.add(activity);
        Log.i("ActivityManager", "push:"+activity.getClass().getName());
    }

    //退出栈中所有Activity 
    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            FragmentActivity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }
}
