package com.clannad.ui.base;

import com.clannad.impl.ConstantValues;

/**
 * Created by 御轩 on 16/9/30 下午3:27.
 * APP状态的管理类
 */

public class AppStatusTracker {
    private static AppStatusTracker tracker;
    private int mAppStatus = ConstantValues.STATUS_FORCE_KILLED;

    private AppStatusTracker() {

    }

    public static AppStatusTracker getInstance() {
        if (tracker == null) {
            tracker = new AppStatusTracker();
        }
        return tracker;
    }

    public int getAppStatus() {
        return mAppStatus;
    }

    public void setAppStatus(int mAppStatus) {
        this.mAppStatus = mAppStatus;
    }
}

