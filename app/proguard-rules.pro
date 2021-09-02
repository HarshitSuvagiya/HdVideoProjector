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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-allowaccessmodification
-optimizations !code/simplification/arithmetic


-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class androidx.appcompat.widget.AppCompatTextView

-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.app.** { *; }
-keep public class android.support.design.widget.** { *; }
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
-dontwarn com.caverock.androidsvg.**

-dontwarn com.google.android.material.**
-dontwarn okhttp3.**
-dontwarn okio.**

-ignorewarnings