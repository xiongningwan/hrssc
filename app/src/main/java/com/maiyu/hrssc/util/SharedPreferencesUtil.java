package com.maiyu.hrssc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.maiyu.hrssc.base.bean.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * @param context
     * @param time
     */
    public static void savePopUpTime(Context context, long time) {
        SharedPreferences perfer = context.getSharedPreferences("jhj_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putLong("PopUpTime", time);
        editor.commit();
    }

    /**
     * 得到保存的弹窗时间
     * @param context
     * @return
     */
    public static long getPopUpTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences("jhj_info", Context.MODE_PRIVATE);
        return sp.getLong("PopUpTime", 0);
    }



    /**
     * 保存用户基本信息
     * @param context
     * @param user
     * @param activeTime
     */
    public static void saveUserBaseInfo(Context context, User user, long activeTime) {
        SharedPreferences perfer = context.getSharedPreferences("jhj_info_vp", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putLong("userId", user.getId());
        editor.putString("mobile", user.getMobile());
        editor.putString("token", user.getToken());

        editor.putString("level", user.getLevel());
        editor.putString("realityName", user.getRealityName());
        editor.putString("code", user.getCode());
        editor.putInt("incomeSpace", user.getIncomeSpace());
        editor.putInt("incomeSpace", user.getIncomeSpace());
        editor.putInt("isAssess", user.getIsAssess());
        editor.putInt("isIdCard", user.getIsIdCard());
        editor.putLong("activeTime", activeTime);
        editor.commit();

    }

    /**
     * 用户基本信息
     * @param context
     * @return
     */
    public static Map<String, Object> getUserBaseInfo(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("jhj_info_vp", Context.MODE_PRIVATE);
        Map<String, Object> map = new HashMap<String, Object>();
        User u = new User();
        u.setId(perfer.getLong("userId", -1));
        u.setMobile(perfer.getString("mobile", null));
        u.setToken(perfer.getString("token", null));

        u.setLevel(perfer.getString("level", null));
        u.setRealityName(perfer.getString("realityName", null));
        u.setCode(perfer.getString("code", null));
        u.setIncomeSpace(perfer.getInt("incomeSpace", 1));
        u.setIsAssess(perfer.getInt("isAssess", 0));
        u.setIsIdCard(perfer.getInt("isIdCard", 0));
        long activeTime = perfer.getLong("activeTime", 0);

        map.put("user", u);
        map.put("activeTime", activeTime);
        return map;
    }


    /**
     * 保存最新版本
     * @param context
     * @param lastVersion
     */
    public static void saveAppLastVersion(Context context, String lastVersion) {
        SharedPreferences perfer = context.getSharedPreferences("jhj_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putString("LastVersion", lastVersion);
        editor.commit();
    }

    /**
     * 获取最新版本
     * @param context
     * @return
     */
    public static String getAppLastVersion(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("jhj_info", Context.MODE_PRIVATE);
        String lastVersion = perfer.getString("LastVersion", "");
        return lastVersion;
    }



}
