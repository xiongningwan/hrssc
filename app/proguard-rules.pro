# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\ProgramFiles\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*





##反射
-keepattributes Signature
-keepattributes EnclosingMethod

-keep class com.maiyu.hrssc.base.bean.**{*;}
-keep class com.maiyu.hrssc.util.EngineFactory{*;}
-keep class com.maiyu.hrssc.base.engine.**{*;}
-keep class com.maiyu.hrssc.integration.bean.**{*;}
-keep class com.maiyu.hrssc.my.activity.bean.**{*;}
-keep class com.maiyu.hrssc.home.bean.**{*;}
-keep class com.maiyu.hrssc.home.activity.applying.bean.**{*;}
-keep class com.maiyu.hrssc.home.activity.funds.bean.**{*;}
-keep class com.maiyu.hrssc.home.activity.information.bean.**{*;}
-keep class com.maiyu.hrssc.home.activity.socialsecurity.bean.**{*;}
-keep class com.maiyu.hrssc.home.activity.todo.bean.**{*;}
#-keep class com.maiyu.hrssc.activity.fragment.*{*;}


## api类
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.Intent
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
native <methods>;
}
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
    public <fields>;
    private <fields>;
}

-keep class * implements java.io.Serializable {
    public <fields>;
    private <fields>;
}









## ----------------------------------
##      webview js
## ----------------------------------
-keepclassmembers class com.maiyu.hrssc.base.activity.WebActivity$JavaScriptInterface {
  public *;
}

-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-dontwarn com.maiyu.hrssc.base.activity.WebActivity**


## ----------------------------------
##      fastjson
## ----------------------------------
-keep class com.alibaba.fastjson.**{*;}
-dontwarn com.alibaba.fastjson.**



## ----------------------------------
##      okhttp okio
## ----------------------------------
-keep class okhttp3.**{*;}
-keep class okio.**{*;}
-dontwarn okhttp3.**
-dontwarn okio.**


## ----------------------------------
##      bugly
## ----------------------------------
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile



## ----------------------------------
##      高德地图
## ----------------------------------
-keep class com.amap.api.**{*;}
-keep class com.loc.**{*;}
-keep class com.autonavi.**
-dontwarn  com.amap.api.**
-dontwarn  com.loc.**
-dontwarn  com.autonavi.**

## ----------------------------------
##      retrofit2
## ----------------------------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }





## ----------------------------------
##      环信部分混淆规则代码
## ----------------------------------
-dontwarn  com.easemob.**
-keep class com.easemob.** {*;}
-keep class org.xmlpull.** {*;}
-keep class com.squareup.picasso.* {*;}
-keep class com.hyphenate.* {*;}
-keep class com.hyphenate.chat.** {*;}
-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
#如果使用easeui库，需要这么写
-keep class com.hyphenate.easeui.utils.EaseSmileUtils {*;}
#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-dontwarn ch.imvs.**
-dontwarn org.slf4j.**
-keep class org.ice4j.** {*;}
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}
-keep class com.easemob.** {*;}
#环信3.0
-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**
-keep class com.superrtc.** {*;}


## ----------------------------------
##      信鸽
## ----------------------------------
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.** {* ;}
-keep class com.tencent.mid.** {* ;}
-dontwarn  com.tencent.android.tpush.**
-dontwarn  com.tencent.mid.**