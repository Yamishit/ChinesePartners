package com.clannad.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clannad.R;
import com.clannad.bean.PhotoGirl;
import com.clannad.common.base.BaseFragment;
import com.clannad.common.commonutils.LogUtils;
import com.clannad.common.commonutils.ToastUitl;
import com.clannad.ui.fragments.contract.PhotoListContract;
import com.clannad.ui.fragments.model.PhotosListModel;
import com.clannad.ui.fragments.presenter.PhotosListPresenter;
import com.clannad.widget.ZitiButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by F_ck on 2016/9/28.
 */

public class FirstFragment extends BaseFragment<PhotosListPresenter,PhotosListModel> implements PhotoListContract.View {


    @Bind(R.id.id_firsttab_reqbt)
    ZitiButton id_firsttab_reqbt;

    private int mStartPage = 1;
    private static int SIZE = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first_tab;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.id_firsttab_reqbt})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.id_firsttab_reqbt:
                mPresenter.getPhotosListDataRequest(SIZE, mStartPage);
                break;
        }
    }

    @Override
    public void returnPhotosListData(List<PhotoGirl> photoGirls) {
        ToastUitl.show(photoGirls.get(0).getDesc(),0);
        LogUtils.logd(photoGirls.toString());
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
