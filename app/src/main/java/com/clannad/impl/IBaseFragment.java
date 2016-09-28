package com.clannad.impl;

import android.os.Bundle;
import android.view.View;

/**
 * Created by 御轩 on 16/9/28 下午5:23.
 */

public interface IBaseFragment {

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID
     *
     * @return 布局文件资源ID
     */
    int getCreateViewLayoutId();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据
     * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    void initData();

    /**
     * 此方法用于设置View显示数据
     */
    void initView(View inflateView, Bundle savedInstanceState);

    void initListener();

}
