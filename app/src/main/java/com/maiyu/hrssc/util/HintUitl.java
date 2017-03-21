package com.maiyu.hrssc.util;

import android.content.Context;
import android.widget.Toast;

/**
 * toast 提示信息显示工具
 *
 */
public class HintUitl {
    public static void toast(Context context, String msg) {
        if(context == null) {
            return;
        }

        if(msg != null && !msg.equals("")) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }
    
    public static void toastShort(Context context, String msg) {
        if(context == null) {
            return;
        }

        if(msg != null && !msg.equals("")) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
