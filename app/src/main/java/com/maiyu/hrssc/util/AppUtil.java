/**
 *
 */
package com.maiyu.hrssc.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AppUtil {

    /**
    * 获取屏幕分辨率
    * @param context
    * @return
    */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = { width, height };
        return result;
    }

    /**
     * 拨打电话
     * @param context
     */
  /*  public static void phoneCall(Context context) {
        String number = context.getString(R.string.phone_number);
        //用intent启动拨打电话  
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }*/

    public static String getMac(Context context) {
        String m_szAndroidID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        return m_szAndroidID;
    }


    public static String md5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
           /* LogHelper.e(TAG, "Method_md5:" + e.getMessage());*/
            return null;
        }
    }

    /**
     * 格式化金额
     * @param money
     * @return
     */
    public static String formatMoney(Double money) {
        DecimalFormat format = new DecimalFormat("###,##0.00");
        return format.format(money);
    }

    /**
     * 格式整数
     * @param num
     * @return
     */
    public static String formatNumber(long num) {
       DecimalFormat format = new DecimalFormat("###,##0");
        return format.format(num);
    }



    /**
     * 隐藏格式化输出身份证
     * @param idCard
     * @return
     */
    public static String formatIdCard(String idCard) {
        if (idCard != null && idCard.length() >= 15) {
            String formatIdCard = idCard.substring(0, 3) + "********" + idCard.substring(11);
            return formatIdCard;
        } else {
            return "";
        }
    }

    /**
     * 隐藏格式化输出手机号码
     * @param no
     * @return
     */
    public static String formatMobile(String no) {
        if (no != null && no.length() == 11) {
            String replaceNum = no.subSequence(0, 3) + "****" + no.substring(7, no.length());
            return replaceNum;
        } else {
            return "";
        }
    }

    // clear the cache before time numDays  
    public static int clearCacheFolder(File dir, long numDays) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, numDays);
                    }
                    if (child.lastModified() < numDays) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

  /*  *//**
     * 网络是否连接
     * @param context
     * @return
     *//*
    public static boolean networkConnected(Context context) {
        FinanceApplication app = (FinanceApplication) context.getApplicationContext();
        if (app.getmNetwork() == ConstantValue.NETWORK_STATE_CONNECTED) {
            return true;
        } else if (app.getmNetwork() == ConstantValue.NETWORK_STATE_DISCONNECTED) {
            return false;
        }
        return true;
    }*/

    //判断市场是否存在的方法
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();//获取packagemanager 
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息 
        List<String> pName = new ArrayList<String>();//用于存储所有已安装程序的包名 
        //从pinfo中将包名字逐一取出，压入pName list中 
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE 
    }

    /**
     * 启动到app详情界面
     * 
     * @param appPkg
     *            App的包名
     * @param marketPkg
     *            应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏输入法
     * @param context
     */
    public void hideSoftInputFromWindow(Context context) {
        //判断隐藏软键盘是否弹出
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        if(isOpen) {
            imm. hideSoftInputFromWindow(((Activity)context).getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取输入框焦点
     * @param context
     * @param mInputView
     */
    public void forceInputViewGetFocus(Context context, EditText mInputView) {
        mInputView.setFocusable(true);
        mInputView.setFocusableInTouchMode(true);
        mInputView.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mInputView, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏输入法
     * @param context
     * @param mInputView
     */
    public void hideSoftInputFromWindow(Context context, EditText mInputView) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mInputView.getWindowToken(),0);

    }
    /*
       * 判断是否安装
       */
    public static boolean isAppInstalled(Context context,String packagename)
    {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        }catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if(packageInfo ==null){
            //System.out.println("没有安装");
            return false;
        }else{
            //System.out.println("已经安装");
            return true;
        }
    }
}
