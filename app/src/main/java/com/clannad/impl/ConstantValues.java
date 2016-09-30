package com.clannad.impl;

/**
 * Created by 御轩 on 16/9/30 下午3:20.
 */

public interface ConstantValues {
    public final static int STATUS_FORCE_KILLED = -1;
    public final static int STATUS_LOGOUT = 0;
    public final static int STATUS_OFFLINE = 1;
    public final static int STATUS_ONLINE = 2;
    public final static int STATUS_KICK_OUT = 3;

    public final static String KEY_HOME_ACTION = "key_home_action";
    public final static String ACTION_BACK_TO_HOME = "action_back_to_home";
    public final static int ACITON_RESTART_APP = 1;
    public final static int ACTION_LOGOUT = 2;
    public final static int ACTION_KICK_OUT = 3;
}
