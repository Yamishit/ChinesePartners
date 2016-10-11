package com.clannad.common.baserx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by F_ck on 2016/10/11.
 * RxJava调度管理
 */

public class RxSchedulers {
    public static <T> Observable.Transformer<T,T> io_main(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
