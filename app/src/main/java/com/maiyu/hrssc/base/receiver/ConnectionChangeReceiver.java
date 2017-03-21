package com.maiyu.hrssc.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.HrsscApplication;
import com.maiyu.hrssc.util.HintUitl;


public class ConnectionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        HrsscApplication app = (HrsscApplication) context.getApplicationContext();
        /* if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
             HintUitl.toastShort(context, "你的网络已断开连接，请检查你的网络");
             //改变背景或者 处理网络的全局变量
             app.setmNetwork(ConstantValue.NETWORK_STATE_DISCONNECTED);
         } else {
             //改变背景或者 处理网络的全局变量
             app.setmNetwork(ConstantValue.NETWORK_STATE_CONNECTED);
         }*/
        NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            app.setmNetwork(ConstantValue.NETWORK_STATE_CONNECTED);
        } else {
            HintUitl.toastShort(context, "你的网络已断开连接，请检查你的网络");
            app.setmNetwork(ConstantValue.NETWORK_STATE_DISCONNECTED);
        }
    }
}
