package com.clannad.ui.fragments.contract;

import com.clannad.bean.PhotoGirl;
import com.clannad.common.base.BaseModel;
import com.clannad.common.base.BasePresenter;
import com.clannad.common.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by F_ck on 2016/10/11.
 */

public interface PhotoListContract {
    interface Model extends BaseModel {
        //请求获取图片
        Observable<List<PhotoGirl>> getPhotosListData(int size, int page);
    }

    interface View extends BaseView {
        //返回获取的图片
        void returnPhotosListData(List<PhotoGirl> photoGirls);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        //发起获取图片请求
        public abstract void getPhotosListDataRequest(int size, int page);
    }

}
