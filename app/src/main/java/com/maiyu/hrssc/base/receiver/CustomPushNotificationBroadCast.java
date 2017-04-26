package com.maiyu.hrssc.base.receiver;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.maiyu.hrssc.base.activity.MainActivity;
import com.maiyu.hrssc.base.activity.MessagesActivity;
import com.maiyu.hrssc.util.AppUtil;
import com.maiyu.hrssc.util.HintUitl;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * 通知被点击触发广播
 * @author xiongning
 *
 * 2015-8-27
 */
public class CustomPushNotificationBroadCast extends XGPushBaseReceiver {
    @Override
    public void onDeleteTagResult(Context arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult message) {
        if (context == null || message == null) {
            return;
        }

        if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
            // 通知在通知栏被点击啦。。。。。
            // APP自己处理点击的相关动作
            // 这个动作可以在activity的onResume也能监听，请看第3点相关内容
            HintUitl.toastShort(context, message.getContent());
            setClickNotifactionStartActivity(context, message);

        } else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
            // 通知被清除啦。。。。
            // APP自己处理通知被清除后的相关动作
        }

    }

    @Override
    public void onNotifactionShowedResult(Context arg0, XGPushShowedResult arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRegisterResult(Context arg0, int arg1, XGPushRegisterResult arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSetTagResult(Context arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextMessage(Context arg0, XGPushTextMessage arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUnregisterResult(Context arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    private void setClickNotifactionStartActivity(Context context, XGPushClickedResult message) {
        if (AppUtil.isAppAlive(context, "com.maiyu.hrssc")) {
            Log.i("NotificationReceiver", "the app process is alive");

            if (JSON.parseObject(message.getCustomContent()).getString("openActivity").equals("true")) {
                Intent i = new Intent(context, MessagesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } else if (JSON.parseObject(message.getCustomContent()).getString("openApp").equals("true")) {

                //ComponentName runningActivityName = getRunningActivityName(context);
                /**
                 * 获取的runningActivityName.getClassName()为com.tencent.android.tpush.XGPushActivity

                 */
                // Log.i("NotificationReceiver", runningActivityName.getClassName());
                //  if ("MainActivity".equals(runningActivityName.getClassName())) {
                //     ((MainActivity) context).tryToMainActivity();
                //  } else {
                Intent in = new Intent(context, MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
                //   }
            }

        } else {
            // 下面这段程序是不会运行，而是信鸽推送在运行，启动的是mainactivity
            //如果app进程已经被杀死，先重新启动app，将DetailActivity的启动参数传入Intent中，参数经过
            //SplashActivity传入MainActivity，此时app的初始化已经完成，在MainActivity中就可以根据传入             //参数跳转到DetailActivity中去了
            Log.i("NotificationReceiver", "the app process is dead");
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.jintoufs");
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);

        }

    }

    private ComponentName getRunningActivityName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName runningActivity = am.getRunningTasks(1).get(0).topActivity;
        return runningActivity;
    }

}
