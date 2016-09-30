package com.clannad.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.clannad.MainActivity;
import com.clannad.R;
import com.clannad.impl.ConstantValues;
import com.clannad.utils.Common;
import com.clannad.widget.SwipeBackLayout;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by F_ck on 2016/9/24.
 */

public abstract class BaseActivity extends AutoLayoutActivity implements ConstantValues {

//    public static final ClannadApi sClannadIO = ClannadFactory.getClannadIOSingleton();
    private CompositeSubscription mCompositeSubscription;

    protected SwipeBackLayout layout;
    String activityName = "";
    public int contentId;
    public Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setContetId());
        ButterKnife.bind(this);
        context = this;
        init();
        switch (AppStatusTracker.getInstance().getAppStatus()) {
            case STATUS_FORCE_KILLED:
                protectApp();
                break;
            case STATUS_KICK_OUT:
                kickOut();
                break;
            case STATUS_ONLINE:
            case STATUS_LOGOUT:
            case STATUS_OFFLINE:
                getRxMsg();
                initData();
                break;
            default:
                break;
        }


    }

    /**
     * 被挤下线
     */
    protected void kickOut() {
        // TODO: 16/9/30  show dialog confirm

    }

    /**
     * 此方法为了解决应用在后台回收而栈信息却被保留下来,此时应该手动强制重新走一遍
     */
    protected void protectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_HOME_ACTION, ACTION_BACK_TO_HOME);
        startActivity(intent);
    }


    private void init() {
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.activity_layout_base, null);
        layout.attachToActivity(this);
        if (Build.VERSION.SDK_INT >= 19) {
            //仅状态栏沉浸
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //状态栏及底部全部沉浸
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //背景为白色的沉浸式 miui  flyme设置状态栏字体颜色
        MyApplication.getInstance().setStatusDark(this, true);
        Common.getInstance().addToActivityList(this);
    }


    public CompositeSubscription getmCompositeSubscription(){
        if(this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeSubscription();
        }
        return this.mCompositeSubscription;
    }

    //与上一个方法有点矛盾
    public void addSubscription(Subscription s){
        if(this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }


    public abstract int setContetId();
    public abstract void initData();
    public abstract void getRxMsg();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if(this.mCompositeSubscription != null){
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
