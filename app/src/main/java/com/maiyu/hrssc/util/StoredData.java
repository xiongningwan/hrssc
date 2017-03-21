package com.maiyu.hrssc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/*
 * Application.onCreate中调用StoredData.getThis().markOpenApp();
 */
public class StoredData {
    public static final int LMODE_NEW_INSTALL = 1; // 启动-模式,首次安装-首次启动、覆盖安装-首次启动、已安装-二次启动
    public static final int LMODE_UPDATE = 2;
    public static final int LMODE_AGAIN = 3;

    private boolean isOpenMarked = false;
    private static int launchMode = LMODE_AGAIN; // 启动-模式

    private static StoredData instance;

    private SharedPreferences share; // 一般信息

    public static StoredData getThis() {
        if (instance == null)
            instance = new StoredData();

        return instance;
    }

    // -------启动状态------------------------------------------------------------

    // 标记-打开app,用于产生-是否首次打开
    public void markOpenApp(Context context) {
        // 防止-重复调用
        if (isOpenMarked)
            return;
        isOpenMarked = true;

        String lastVersion = SharedPreferencesUtil.getAppLastVersion(context);
        String thisVersion = PackageInfoUtil.getVersionName(context);

        // 首次启动
        if (TextUtils.isEmpty(lastVersion)) {
            launchMode = LMODE_NEW_INSTALL;
            SharedPreferencesUtil.saveAppLastVersion(context, thisVersion);
        }
        // 更新
        else if (!thisVersion.equals(lastVersion)) {
            launchMode = LMODE_UPDATE;
            SharedPreferencesUtil.saveAppLastVersion(context, thisVersion);
        }
        // 二次启动(版本未变)
        else
            launchMode = LMODE_AGAIN;
    }

    public static int getLaunchMode() {
        return launchMode;
    }

    // 首次打开,新安装、覆盖(版本号不同)
    public boolean isFirstOpen() {
        return launchMode != LMODE_AGAIN;
    }

}
