package com.maiyu.hrssc.base.engine.impl;

import android.content.Context;
import android.content.Intent;

import com.maiyu.hrssc.base.activity.LoginActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络异常处理
 *
 */
public class BaseEngine extends NetException {
    public void dispatcherException(Context context, String json) throws NetException {
        if(context == null) {
            throw new NetException("901");
        }


        //如果状态码为500，表示服务器端异常，返回JSON为：{"msg":"登录失败","data":"","code":"500"} 格式，msg对应的是异常信息
        try {
            if (json == null) {
                return;
            }
            JSONObject jsonObject = new JSONObject(json);
            String code = "";
            String msg = "";
            if (jsonObject.has("status")) {
                code = jsonObject.getString("status");
            }
            if (jsonObject.has("msg")) {
                msg = jsonObject.getString("msg");
            }
            if (code.equals("1")) {
                return;
            } else if (code.equals("0")) {

                //业务异常
                throw new NetException(msg);
            }  else if (code.equals("2")) {
                //未登录
                clearUserData(context);

                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
                throw new NetException(msg);

            } /*else if (code.equals("403")) {
                // 注销账户  清除用户基本缓存,手势缓存启动未否
                clearUserData(context);

                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
                //用户被锁
                throw new NetException(msg);
            } else if (code.equals("406")) {
                //用户本次登录设备与上次登录设备不同
             *//*   clearUserData(context);

                Intent intent = new Intent(context, LoginByCodeActivity.class);
                context.startActivity(intent);
                throw new NetException(msg);*//*
            }

            else if (code.equals("500")) {
                //服务器异常
                throw new NetException(msg);
            } */else {
                //服务器异常
                throw new NetException(msg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new NetException("您的请求服务器暂时无法响应");
        }
    }

    private void clearUserData(Context context) {
        User user = new User();
        user.setId(-1);
        SharedPreferencesUtil.saveUserBaseInfo(context, user);
        DataCenter.getInstance().setUser(user);
        // DataCenter.getInstance().notifyAccountUnloginAdChange();
    }

}
