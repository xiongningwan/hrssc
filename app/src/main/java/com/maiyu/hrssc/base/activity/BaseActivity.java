package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.receiver.ConnectionChangeReceiver;
import com.maiyu.hrssc.util.StatusBarCompat;

public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;
    /** Activity的计数 */
    private static long startedActivityCount = 0l;
    public int activityState;
    public Handler mViewDataRefreshHandler;
    private ConnectionChangeReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataRefreshHandler = new Handler();
        createActivityImpl();
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.project_color_basic_white_pure));
        initViews();
        initData();
        registerReceiver();
    }

    /**
     * 创建 activity
     */
    public abstract void createActivityImpl();

    /**
     * 初始化Views    
     */
    public abstract void initViews();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化onclick
     * @param v
     */
    public abstract void initOnClick(View v);

    @Override
    public void onClick(View v) {
        initOnClick(v);

    }


    /**
     * 注册网络状态监听
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new ConnectionChangeReceiver();
        this.registerReceiver(myReceiver, filter);
    }

    /**
     * 取消注册网络监听
     */
    private void unregisterReceiver() {
        this.unregisterReceiver(myReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startedActivityCount++;

        if (1 == startedActivityCount) {
            applicationDidEnterForeground();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = ACTIVITY_STOP;
        startedActivityCount--;
        if (0 == startedActivityCount) {
            applicationDidEnterBackground();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
        activityState = ACTIVITY_DESTROY;

        // 防止内存泄漏
        try {
            java.lang.reflect.Field fHandler = FragmentActivity.class.getDeclaredField("mHandler");
            fHandler.setAccessible(true);
            Handler handler = (Handler) fHandler.get(this);
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    /** 
     * Activity的回调函数。当application进入前台时，该函数会被自动调用。 
     */
    private void applicationDidEnterForeground() {
    };

    /** 
     * Activity的回调函数。当application进入后台时，该函数会被自动调用。 
     */
    private void applicationDidEnterBackground() {
    };

    @Override
    public void startActivity(Intent intent) {
        // TODO Auto-generated method stub
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

}
