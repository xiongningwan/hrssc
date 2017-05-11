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
     * 保存是否显示小紅小点
     *
     * @param context
     * @param b
     */
    public static void saveIsPointViewVisibility(Context context, Boolean b) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putBoolean("IsPointViewVisibility", b);
        editor.commit();
    }

    /**
     * 获取小红点显示与否
     *
     * @param context
     * @return
     */
    public static Boolean getIsPointViewVisibility(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Boolean isPVVB = perfer.getBoolean("IsPointViewVisibility", false);
        return isPVVB;
    }


    /**
     * 保存登录帐号和密码
     *
     * @param context
     * @param loginName
     * @param loginPwd
     * @param loginType
     */
    public static void saveLoginNamePwd(Context context, String loginName, String loginPwd, String loginType) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putString("loginName", loginName);
        editor.putString("loginPwd", loginPwd);
        editor.putString("loginType", loginType);
        editor.commit();
    }


    /**
     * 获取保存的登录帐号和密码
     *
     * @param context
     * @return
     */
    public static Map<String, String> getLoginNamePwd(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String loginName = perfer.getString("loginName", "");
        String loginPwd = perfer.getString("loginPwd", "");
        String loginType = perfer.getString("loginType", "");
        Map<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("loginPwd", loginPwd);
        map.put("loginType", loginType);
        return map;
    }


    /**
     * 保存特殊参数说明设置
     * 1 户口卡借用：
     * 借用事由：          brief
     * 是否使用集体户：comment   是/否
     * 2 市内户口迁入：
     * 申请说明：           brief
     * 3 户口迁出至市内：
     * 申请说明：            brief
     * 4 市外户口迁入：
     * 申请说明：           brief
     * 5 学位验证：
     * 验证指引 ：这个和办理说明重复，去掉。
     * 6 工卡照片：
     * 无特殊字段
     * 7 预约入职：
     * 预约入职日期 ：     brief
     * (格式：yyyy-MM-dd HH:mm:ss)
     * <p>
     * 8 居住证办理：
     * 申请说明：     brief
     * 9 档案借阅：
     * 申请说明 ：    brief
     *
     * @param context
     * @param i
     */
    public static void saveSpecialParamSet(Context context, int i) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor editor = perfer.edit();
        editor.putInt("SpecialParamSet", i);
        editor.commit();
    }

    /**
     * 获取特殊参数说明设置
     *
     * @param context
     * @return
     */
    public static int getSpecialParamSet(Context context) {
        SharedPreferences perfer = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int i = perfer.getInt("SpecialParamSet", 0);
        return i;
    }
}
