package com.clannad.ui.base;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.graphics.Typeface;
import android.view.Window;
import android.view.WindowManager;


import com.clannad.utils.Common;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



/**
 * Created by Administrator on 2016/3/8.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
//    public static PatchManager mPatchManager;
//    private static final String TAG = "AndFix";
    public static String VERSION_NAME = "";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //Autolayout使用物理百分比
        AutoLayoutConifg.getInstance().useDeviceSize();
//        FreelineCore.init(this);
//        Fresco.initialize(this);
//        NoHttp.initialize(this);
//        LogUtils.d("ssss");
//        SDKInitializer.initialize(getApplicationContext());
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        JPushInterface.setLatestNotificationNumber(getApplicationContext(), 1);
//        LogUtils.d("IMEI = " + ExampleUtil.getImei(this,"") + "== APPKEY = " + ExampleUtil.getAppKey(this));
        try {
            // 加载字体
            PackageInfo mPackageInfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            VERSION_NAME = mPackageInfo.versionName;
            Common.getInstance().TYPE_FACE = Typeface.createFromAsset(getAssets(), "fonts/fzltxhjw.ttf");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        initAndFix();
    }

    /**
     * AndFix  most device will be crash
     * @return
     */
//    private void initAndFix() {
//        // initialize
//        mPatchManager = new PatchManager(this);
//        mPatchManager.init(VERSION_NAME);
//        Log.d(TAG, "inited.");
//        // load patch
//        mPatchManager.loadPatch();
////        Log.d(TAG, "apatch loaded.");
//    }

    public static MyApplication getInstance() {
        return instance;
    }

    public void setStatusDark(Activity activity, boolean isDark) {
        setMiuiStatusBarDarkMode(activity, isDark);
        setMeizuStatusBarDarkIcon(activity, isDark);
    }

    private boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        try {
            Class<? extends Window> clazz = activity.getWindow().getClass();
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
