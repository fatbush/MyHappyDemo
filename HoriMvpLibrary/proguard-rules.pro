# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5   # 指定代码的压缩级别
-dontusemixedcaseclassnames # 混淆时不会产生形形色色的类名
-dontskipnonpubliclibraryclasses    #指定不去忽略非公共的库类
-dontpreverify  # 不预校验
-verbose    # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*    # 混淆时所采用的算法
-ignorewarnings #忽略警告
-renamesourcefileattribute SourceFile   #设置源文件中给定的字符串常量
-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature   #保护给定的可选属性

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends com.google.inject.AbstractModule
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-keepclasseswithmembers class * {
    public <init> (android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init> (android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * implements android.os.Parcelable { static android.os.Parcelable$Creator *; }
-keepclassmembers class **.R$* { public static <fields>; }
-keepclasseswithmembernames class * { native <methods>; }
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * extends android.app.Activity {
	public void *(android.view.View);
}
-keepclassmembers class * {
	@com.google.inject.Inject <init>(...);
	@com.google.inject.Inject <fields>;
	@javax.annotation.Nullable <fields>;
}
-keepclassmembers class * {
	void *(net.eworldui.videouploader.events.*);
}
-keepclassmembers class * {
	void *(roboguice.activity.event.*);
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}


-keepattributes *Annotation*
-keepattributes *JavascriptInterface*


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

##---------------End: proguard configuration for Gson  ----------

#gson
-dontwarn com.google.gson.**
-keep class com.google.gson.** { *; }

#rxjava
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }

#okhttp
-dontwarn okio.**
-keep class okio.** { *; }
-dontwarn okhttp3.**


# Retrofit
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions


-keep class com.hori.horimvplibrary.** { *; }
-dontwarn com.hori.horimvplibrary.**



#androidannotations
-keep class org.androidannotations.** {*;}
-dontwarn org.androidannotations.**



#==================protobuf======================
-dontwarn com.google.**
-keep class com.google.protobuf.**{*;}

