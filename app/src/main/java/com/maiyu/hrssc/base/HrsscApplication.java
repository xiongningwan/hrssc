package com.maiyu.hrssc.base;

import android.app.Application;

import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.util.ActivityManager;
import com.maiyu.hrssc.util.ImageLoaderUtil;
import com.maiyu.hrssc.util.StoredData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HrsscApplication extends Application {
    private int mNetwork;
    private ActivityManager activityManager = null;
    @Override
    public void onCreate() {
        super.onCreate();
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
        
        CrashReport.initCrashReport(getApplicationContext(), "549197d60d", false);



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
    
}
