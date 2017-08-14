package com.maiyu.hrssc.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.util.ActivityManager;
import com.maiyu.hrssc.util.ImageLoaderUtil;
import com.maiyu.hrssc.util.LogHelper;
import com.maiyu.hrssc.util.StoredData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HrsscApplication extends Application {
    private static HrsscApplication mInstance;
    private int mNetwork;
    private ActivityManager activityManager = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // 初始化数据
        DataCenter.getInstance();

        // Initialize ImageLoader with configuration.
        ImageLoaderUtil.initImageLoader(this);
        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();

        // OkHttpUtils 初始化
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        //  初始化app启动模式储存器
        StoredData.getThis().markOpenApp(this);

       //初始化自定义Activity管理器
        activityManager = ActivityManager.getScreenManager();



        //Kefu sdk 初始化简写方式：
        ChatClient.getInstance().init(this, new ChatClient.Options().setAppkey("1103170512115823#kefuchannelapp41283").setTenantId("41283"));
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);


        // 初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), "01a15896cd", true);

        // 信鸽
        initXg();
    }

    public int getmNetwork() {
        return mNetwork;
    }

    public void setmNetwork(int mNetwork) {
        this.mNetwork = mNetwork;
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }
    public void setActivityManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }
    public static HrsscApplication getInstance() {
        return mInstance;
    }


    public boolean isMainProcess() {
        android.app.ActivityManager am = ((android.app.ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<android.app.ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (android.app.ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public void initXg() {



        // 在主进程设置信鸽相关的内容
        if (isMainProcess()) {
            // 为保证弹出通知前一定调用本方法，需要在application的onCreate注册
            // 收到通知时，会调用本回调函数。
            // 相当于这个回调会拦截在信鸽的弹出通知之前被截取
            // 一般上针对需要获取通知内容、标题，设置通知点击的跳转逻辑等等




            XGPushManager
                    .setNotifactionCallback(new XGPushNotifactionCallback() {

                        @Override
                        public void handleNotify(XGNotifaction xGNotifaction) {
                            LogHelper.i("test", "处理信鸽通知：" + xGNotifaction);
                            // 获取标签、内容、自定义内容
                            String title = xGNotifaction.getTitle();
                            String content = xGNotifaction.getContent();
                            String customContent = xGNotifaction
                                    .getCustomContent();
                            // 其它的处理
                            // 如果还要弹出通知，可直接调用以下代码或自己创建Notifaction，否则，本通知将不会弹出在通知栏中。
                            xGNotifaction.doNotify();
                        }
                    });
        }
    }


}
