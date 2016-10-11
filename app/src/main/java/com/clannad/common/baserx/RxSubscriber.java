package com.clannad.common.baserx;

import android.app.Activity;
import android.content.Context;

import com.clannad.R;
import com.clannad.common.baseapp.BaseApplication;
import com.clannad.common.commonutils.NetWorkUtils;
import com.clannad.common.commonwidget.LoadingDialog;

import rx.Subscriber;

/**
 * Created by F_ck on 2016/10/11.
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context context;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动Dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = false;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.context = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, BaseApplication.getAppResources().getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, BaseApplication.getAppResources().getString(R.string.loading), showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading((Activity) context, msg, showDialog);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if(showDialog)
            LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if(!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())){
            _onError(BaseApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器
        else if(e instanceof ServerException){
            _onError(e.getMessage());
        }
        //其他出错
        else{
            _onError(BaseApplication.getAppContext().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected  abstract void _onError(String message);



}
