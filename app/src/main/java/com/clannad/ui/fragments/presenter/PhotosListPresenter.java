package com.clannad.ui.fragments.presenter;


import com.clannad.R;
import com.clannad.bean.PhotoGirl;
import com.clannad.common.baserx.RxSubscriber;
import com.clannad.ui.fragments.contract.PhotoListContract;

import java.util.List;

/**
 * des:图片列表
 */
public class PhotosListPresenter extends PhotoListContract.Presenter {
    @Override
    public void getPhotosListDataRequest(int size, int page) {
        mRxManager.add(mModel.getPhotosListData(size, page)
                .subscribe(new RxSubscriber<List<PhotoGirl>>(mContext, false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(List<PhotoGirl> photoGirls) {
                mView.returnPhotosListData(photoGirls);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
