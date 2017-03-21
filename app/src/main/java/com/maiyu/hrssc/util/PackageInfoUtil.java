package com.maiyu.hrssc.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageInfoUtil {
    /**
     * 获取版本码
     * @return 当前应用的版本码
     */
    public static long getVersionCode(Context context) {
        long code = 1;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return code;
        }
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public static String getVersionName(Context context) {
        String name = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return name;
        }
    }
}
