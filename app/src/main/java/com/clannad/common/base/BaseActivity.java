package com.clannad.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.clannad.R;
import com.clannad.common.BuildConfig;
import com.clannad.common.baseapp.AppManager;
import com.clannad.common.baserx.RxManager;
import com.clannad.common.commonutils.TUtil;
import com.clannad.common.commonutils.ToastUitl;
import com.clannad.common.commonwidget.LoadingDialog;
import com.clannad.common.commonwidget.StatusBarCompat;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by F_ck on 2016/9/24.
 * * 　　　　　　　　┏┓　  ┏┓+ +
 * 　　　　　　　┏┛┻━━━━┛┻ ┓ + +
 * 　　　　　　　┃　　　　  ┃
 * 　　　　　　　┃　　　━　 ┃ ++ + + +
 * 　　　　　　 ████━████  ┃+
 * 　　　　　　　┃　　　　　┃ +
 * 　　　　　　　┃　　　┻　 ┃
 * 　　　　　　　┃　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 */

//public abstract class BaseActivity extends AutoLayoutActivity {
//
//    public static final ClannadApi sClannadIO = ClannadFactory.getClannadIOSingleton();
//    private CompositeSubscription mCompositeSubscription;
//
//    protected SwipeBackLayout layout;
//    String activityName = "";
//    public int contentId;
//    public Activity context;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(setContetId());
//        ButterKnife.bind(this);
//        context = this;
//        init();
//        initData();
//        getRxMsg();
//    }
//
//
//    private void init() {
//        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
//                R.layout.activity_layout_base, null);
//        layout.attachToActivity(this);
//        if (Build.VERSION.SDK_INT >= 19) {
//            //仅状态栏沉浸
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //状态栏及底部全部沉浸
////            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//        //背景为白色的沉浸式 miui  flyme设置状态栏字体颜色
//        MyApplication.getInstance().setStatusDark(this, true);
//        Common.getInstance().addToActivityList(this);
//    }
//
//
//    public CompositeSubscription getmCompositeSubscription(){
//        if(this.mCompositeSubscription == null){
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//        return this.mCompositeSubscription;
//    }
//
//    //与上一个方法有点矛盾
//    public void addSubscription(Subscription s){
//        if(this.mCompositeSubscription == null){
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//        this.mCompositeSubscription.add(s);
//    }
//
//
//    public abstract int setContetId();
//    public abstract void initData();
//    public abstract void getRxMsg();
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ButterKnife.unbind(this);
//        if(this.mCompositeSubscription != null){
//            this.mCompositeSubscription.unsubscribe();
//        }
//    }
//}

    /**************使用样例***************/
//    //1.mvp模式
//    public class MvpActivity extends BaseActivity<MvpPresenter,MvpModel> implements MvpContract.View{
//
//        @Override
//        public int getLayoutId(){
//            return R.layout.mvp_layout;
//        }
//
//        @Override
//        public void initPresenter(){
//            mPresenter.setVM(this,mModel);
//        }
//
//        @Override
//        public void initView(){
//
//        }
//
//    }
//
//    //2.普通模式
//    public class NormalActivity extends BaseActivity{
//        @Override
//        public int getLayoutId(){
//            return R.layout.normal_layout;
//        }
//
//        @Override
//        public void initPresenter(){
//        }
//
//        @Override
//        public void initView(){
//        }
//    }


//MVP基类
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AutoLayoutActivity {
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initView();
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        //设置昼夜主题
        initTheme();
        //把activity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //默认着色状态栏
        setStatusBarColor();
    }

    /********************
     * 抽象方法,子类实现
     *************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化View
    public abstract void initView();

    /**
     * 设置主题
     */
    private void initTheme() {
//        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    /**
     * 着色状态栏(4.4系统以上有效)
     * 默认颜色
     */
    protected void setStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏(4.4系统以上有效)
     * 自己设置颜色
     */
    protected void setStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 沉浸状态栏
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    /**
     * 通过Class跳转界面
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     */
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /*未剥离*/

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     */
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(来自id)
     */
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     */
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 长时间显示Toast提示(来自res)
     */
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 带图片的toast
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(), R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //debug版本不统计crash
        if(!BuildConfig.LOG_DEBUG){
            //友盟统计
            MobclickAgent.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //debug版本不统计crash
        if(!BuildConfig.LOG_DEBUG){
            //友盟统计
            MobclickAgent.onPause(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }
}
