package com.maiyu.hrssc.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.maiyu.hrssc.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * 作者：哇牛Aaron
 * 作者简书文章地址: http://www.jianshu.com/users/07a8b5386866/latest_articles
 * 时间: 2016/11/18
 * 功能描述: OkHttp + notification 下载的服务类
 * OkHttp下载框架hongyang大神封装的好的库 链接:https://github.com/hongyangAndroid/okhttputils
 */
public class DownloadService extends Service {
   /* private static final String DOWN_APK_URL = "http://219.238.2.164/apk.r1.market.hiapk.com/" +
            "data/upload/apkres/2016/11_10/11/com.ss.android.article.news_115902.apk?" +
            "wsiphost=local";*/

    private String DOWN_APK_URL;
    private static final String TAG = DownloadService.class.getSimpleName();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    //显示 Notification
                    showNotificationProgress(msg.arg1);
                    break;
                case 2:
                    //安装apk
                    installApk();
                    break;
            }
        }
    };

    private Boolean autoDownLoad = false;
    private int currentProgress = 0;

    private NotificationManager manager;
    private static final String APK_NAME = "hrssc.apk";


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DOWN_APK_URL = intent.getStringExtra("url");
        //hongyang大神封装好的okHttp网络请求
        //启动分线程下载
        new Thread(new Runnable() {
            @Override
            public void run() {
                okHttpDownLoadApk(DOWN_APK_URL);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 联网下载最新版本apk
     */
    private void okHttpDownLoadApk(final String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()// Environment.getExternalStorageDirectory().getAbsolutePath() 存储路径
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), APK_NAME) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        //Log.e(TAG, "onResponse() 当前线程 == " + Thread.currentThread().getName());
                        Log.e(TAG, "onResponse :" + response.getAbsolutePath());
                    }

                    @Override
                    public void inProgress(final float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        //Log.e(TAG, "inProgress() 当前线程 == " + Thread.currentThread().getName());
                        // autoDownLoad = (Boolean) SPUtils.get(DownloadService.this, SPUtils.WIFI_DOWNLOAD_SWITCH, false);
                        //判断开关状态 开 则静默下载
                        if (autoDownLoad) {
                            //说明自动更新 这里服务在后台默默运行下载 只能看日志了
                            //Log.e(TAG, "自动安装的进度 == " + (100 * progress));

                            if ((100 * progress) == 100.0) {
                                //Log.e(TAG, "网络请求 自动安装 当前线程 == " + Thread.currentThread().getName());

                                handler.sendEmptyMessage(2);
                            }

                        } else {//否则 进度条下载

                            int pro = (int) (100 * progress);

                            //解决pro进度重复传递 progress的问题 这里解决UI界面卡顿问题
                            if (currentProgress < pro && pro <= 100) {
                                //Log.e(TAG, "进入");

                                currentProgress = pro;

                                Message msg = new Message();
                                msg.what = 1;
                                msg.arg1 = currentProgress;
                                handler.sendMessage(msg);
                            }

                        }
                    }
                });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotificationProgress(int progress) {
        //Log.e(TAG, "进度 == " + progress);
        String message = "当前下载进度: " + progress + "%";
        int AllProgress = 100;


        Intent intent = null;
        if (progress == 100) {
            message = "下载完毕，点击安装";
            //Log.e(TAG, "下载完成 " + progress);

            //安装apk
            installApk();
            if (manager != null) {
                manager.cancel(0);//下载完毕 移除通知栏
            }

            //当进度为100%时 传入安装apk的intent
            File fileLocation = new File(Environment.getExternalStorageDirectory(), APK_NAME);
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(FileProvider.getUriForFile(this, "com.maiyu.hrssc.fileprovider", fileLocation), "application/vnd.android.package-archive");
            } else {
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setDataAndType(Uri.fromFile(fileLocation), "application/vnd.android.package-archive");
            }
        }
        if (intent == null) {
            intent = new Intent(); //暂时没有更好的办法 保证不能为空即可
        }
        //表示返回的PendingIntent仅能执行一次，执行完后自动取消
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.logo)//App小的图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo))//App大图标
                .setContentTitle("HRSSC最新版本更新")//设置通知的信息
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setContentText(message)
                .setAutoCancel(false)//用户点击后自动删除
                //.setDefaults(Notification.DEFAULT_LIGHTS)//灯光
                .setPriority(Notification.PRIORITY_DEFAULT)//设置优先级
                .setOngoing(true)
                .setProgress(AllProgress, progress, false) //AllProgress最大进度 //progress 当前进度
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, notification);
    }

    /**
     * 安装apk
     */
    private void installApk() {
        //Environment.getExternalStorageDirectory() 保存的路径
        Log.e(TAG, "installApk运行了");

        File fileLocation = new File(Environment.getExternalStorageDirectory(), APK_NAME);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(FileProvider.getUriForFile(this, "com.maiyu.hrssc.fileprovider", fileLocation), "application/vnd.android.package-archive");
        } else {
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setDataAndType(Uri.fromFile(fileLocation), "application/vnd.android.package-archive");
        }
        startActivity(intent);


        //停止服务
        stopSelf();
    }

    @Override
    public void onDestroy() {
        //Log.e(TAG, "onDestroy()");
        super.onDestroy();

        /*if (manager != null) {
            manager.cancel(0);//下载完毕 移除通知栏
        }*/
    }
}