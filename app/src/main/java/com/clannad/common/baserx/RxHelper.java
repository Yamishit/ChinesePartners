package com.clannad.common.baserx;

import com.clannad.common.basebean.BaseRespose;
import com.clannad.common.commonutils.LogUtils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by F_ck on 2016/10/11.
 */

public class RxHelper {

    /**
     * 对API返回的数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseRespose<T>, T> handleResult() {
        return new Observable.Transformer<BaseRespose<T>, T>() {

            @Override
            public Observable<T> call(Observable<BaseRespose<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseRespose<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseRespose<T> result) {
                        LogUtils.logd("result from api : " + result);
                        if (result.success()) {
                            return createData(result.data);
                        } else {
                            return Observable.error(new ServerException(result.msg));
                        }
                    }
                });
            }
        };
    }

    public static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
