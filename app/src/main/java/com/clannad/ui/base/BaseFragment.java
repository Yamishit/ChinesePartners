package com.clannad.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clannad.impl.IBaseFragment;
import com.clannad.utils.Common;

/**
 * Created by 御轩 on 16/9/28 下午4:59.
 */

public  abstract class BaseFragment extends android.app.Fragment implements IBaseFragment {
    private boolean isAttached = false;
    /**
     * Fragment Content view
     */
    private View inflateView;

    /**
     * 记录是否已经创建了,防止重复创建
     */
    private boolean viewCreated;

    protected Activity mContext;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        if (!viewCreated) {
            viewCreated = true;
            initData();
        }
    }

    @Nullable
    @Override
    public  final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == inflateView) {
            int layoutResId = getCreateViewLayoutId();
            if (layoutResId > 0)
                inflateView = inflater.inflate(getCreateViewLayoutId(), container, false);
        }
        return inflateView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttached = true;
        Common.getInstance().addToFragmentList(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewCreated) {
            initView(view, savedInstanceState);
            initListener();
            viewCreated = false;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        isAttached = false;
        Common.getInstance().removeFragment(this);
    }

    /**
     * 子类只要重写这个方法传入布局即可
     * @return
     */
    @Override
    public int getCreateViewLayoutId() {
        return 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 解决ViewPager中的问题
        if (null != inflateView) {
            ((ViewGroup) inflateView.getParent()).removeView(inflateView);
        }
    }
}
