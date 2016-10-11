package com.clannad.ui.fragments.model;


import com.clannad.api.Api;
import com.clannad.api.HostType;
import com.clannad.bean.GirlData;
import com.clannad.bean.PhotoGirl;
import com.clannad.common.baserx.RxSchedulers;
import com.clannad.ui.fragments.contract.PhotoListContract;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class PhotosListModel implements PhotoListContract.Model{
    @Override
    public Observable<List<PhotoGirl>> getPhotosListData(int size, int page) {
        return Api.getDefault(HostType.GANK_GIRL_PHOTO)
                .getPhotoList(Api.getCacheControl(),size, page)
                .map(new Func1<GirlData, List<PhotoGirl>>() {
                    @Override
                    public List<PhotoGirl> call(GirlData girlData) {
                        return girlData.getResults();
                    }
                })
                .compose(RxSchedulers.<List<PhotoGirl>>io_main());
    }
}
