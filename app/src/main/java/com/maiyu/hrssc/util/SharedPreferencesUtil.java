package com.maiyu.hrssc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.maiyu.hrssc.base.bean.User;

import java.util.Date;

public class SharedPreferencesUtil {

    public static boolean isPopUp(Context context, long period) {
        Date d = new Date();
        long longtime = d.getTime();
        if (getPopUpTime(context) == 0) {
            savePopUpTime(context, longtime);
            return true;
        } else if ((longtime - getPopUpTime(context)) >= period) {
            savePopUpTime(context, longtime);//每次弹窗时，都记录下一个当前时间
            return true;
        }
        return false;
    }

    /**
     * 保存弹窗时间
     *
     * @param context
     * @param time
     */
    public static void savePopUpTime(Context context, long time) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putLong("PopUpTime", time);
        editor.commit();
    }

    /**
     * 得到保存的弹窗时间
     *
     * @param context
     * @return
     */
    public static long getPopUpTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return sp.getLong("PopUpTime", 0);
    }


    /**
     * 保存用户基本信息
     *
     * @param context
     * @param user
     */
    public static void saveUserBaseInfo(Context context, User user) {
        SharedPreferences perfer = context.getSharedPreferences("user_info_vp", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putString("account", user.getAccount());
        editor.putString("uin", user.getUin());
        editor.putLong("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("signature", user.getSignature());
        editor.putString("head", user.getHead());
        editor.putString("password", user.getPassword());
        editor.putString("status", user.getStatus());
        editor.putString("sex", user.getSex());
        editor.putString("birthday", user.getBirthday());
        editor.putString("marry", user.getMarry());
        editor.putString("minority", user.getMinority());
        editor.putString("id_card", user.getId_card());
        editor.putString("amount", user.getAmount());
        editor.putString("phone", user.getPhone());
        editor.putString("email", user.getEmail());
        editor.putString("login_time", user.getLogin_time());
        editor.putString("create_time", user.getCreate_time());
        editor.putString("token", user.getToken());
        editor.putString("last_loginTime", user.getLast_loginTime());
        editor.putString("last_loginIp", user.getLast_loginIp());
        editor.putString("signed", user.getSigned());

        editor.commit();

    }

    /**
     * 用户基本信息
     *
     * @param context
     * @return
     */
    public static User getUserBaseInfo(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("user_info_vp", Context.MODE_PRIVATE);
        User u = new User();
        u.setAccount(perfer.getString("account", null));
        u.setUin(perfer.getString("uin", null));
        u.setId(perfer.getLong("id", -1));
        u.setName(perfer.getString("name", null));
        u.setSignature(perfer.getString("signature", null));
        u.setHead(perfer.getString("head", null));
        u.setPassword(perfer.getString("password", null));
        u.setStatus(perfer.getString("status", null));
        u.setSex(perfer.getString("sex", null));
        u.setBirthday(perfer.getString("birthday", null));
        u.setMarry(perfer.getString("marry", null));
        u.setMinority(perfer.getString("minority", null));
        u.setId_card(perfer.getString("id_card", null));
        u.setAmount(perfer.getString("amount", null));
        u.setPhone(perfer.getString("phone", null));
        u.setEmail(perfer.getString("email", null));
        u.setLogin_time(perfer.getString("login_time", null));
        u.setCreate_time(perfer.getString("create_time", null));
        u.setToken(perfer.getString("token", null));
        u.setLast_loginTime(perfer.getString("last_loginTime", null));
        u.setLast_loginIp(perfer.getString("last_loginIp", null));
        u.setSigned(perfer.getString("signed", null));

        return u;
    }


    /**
     * 保存最新版本
     *
     * @param context
     * @param lastVersion
     */
    public static void saveAppLastVersion(Context context, String lastVersion) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putString("LastVersion", lastVersion);
        editor.commit();
    }

    /**
     * 获取最新版本
     *
     * @param context
     * @return
     */
    public static String getAppLastVersion(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String lastVersion = perfer.getString("LastVersion", "");
        return lastVersion;
    }


    /**
     * 保存城市名称
     *
     * @param context
     * @param city
     */
    public static void saveCityName(Context context, String city) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putString("city", city);
        editor.commit();
    }

    /**
     * 获取城市名称
     *
     * @param context
     * @return
     */
    public static String getCityName(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String city = perfer.getString("city", "深圳");
        return city;
    }


    /**
     * 保存登录帐号
     *
     * @param context
     * @param loginName
     */
    public static void saveLoginName(Context context, String loginName) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putString("loginName", loginName);
        editor.commit();
    }


    /**
     * 获取保存的登录帐号
     *
     * @param context
     * @return
     */
    public static String getLoginName(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String loginName = perfer.getString("loginName", "");
        return loginName;
    }
}
