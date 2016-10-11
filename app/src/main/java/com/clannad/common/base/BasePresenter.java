package com.clannad.common.base;

import android.content.Context;

import com.clannad.common.baserx.RxManager;


/**
 * Created by F_ck on 2016/10/10.
 * 基类Persenter
 */

public abstract class BasePresenter<T,E> {

    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(T v,E m){
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    private void onStart() {
    }
    public void onDestroy(){
        mRxManager.clear();
    }


}
