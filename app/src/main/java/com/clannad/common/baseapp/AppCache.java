package com.clannad.common.baseapp;

import android.content.Context;

/**
 * Created by F_ck on 2016/10/11.
 * App内存缓存
 *
 * Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。
 * 而且，当成员变量发生变化时，强迫线程将变化值回写到共享内存。
 * 这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
 * 在两个或者更多的线程访问的成员变量上使用volatile。
 * 当要访问的变量已在synchronized代码块中，或者为常量时，不必使用。
 *
 */


public class AppCache {
    private Context context;
    private volatile static AppCache instance;
    private String token;
    private String userId = "1000";
    private String userName = "康";
    private String icon = "Image/20161010/199111091391.jpeg";

    private AppCache(){}

    public static AppCache getInstance(){
        if(null == instance){
            synchronized (AppCache.class){
                if(instance == null){
                    instance = new AppCache();
                }
            }
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
