package com.clannad.common.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by F_ck on 2016/10/11.
 */
/**
 * ************************************************************************
 * **                              _oo0oo_                               **
 * **                             o8888888o                              **
 * **                             88" . "88                              **
 * **                             (| -_- |)                              **
 * **                             0\  =  /0                              **
 * **                           ___/'---'\___                            **
 * **                        .' \\\|     |// '.                          **
 * **                       / \\\|||  :  |||// \\                        **
 * **                      / _ ||||| -:- |||||- \\                       **
 * **                      | |  \\\\  -  /// |   |                       **
 * **                      | \_|  ''\---/''  |_/ |                       **
 * **                      \  .-\__  '-'  __/-.  /                       **
 * **                    ___'. .'  /--.--\  '. .'___                     **
 * **                 ."" '<  '.___\_<|>_/___.' >'  "".                  **
 * **                | | : '-  \'.;'\ _ /';.'/ - ' : | |                 **
 * **                \  \ '_.   \_ __\ /__ _/   .-' /  /                 **
 * **            ====='-.____'.___ \_____/___.-'____.-'=====             **
 * **                              '=---='                               **
 * ************************************************************************
 * **                        佛祖保佑      镇类之宝                         **
 * ************************************************************************
 *
 */
public class BaseApplication extends Application{

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext(){
        return baseApplication;
    }

    public static Resources getAppResources(){
        return baseApplication.getResources();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
