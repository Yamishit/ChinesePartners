package com.clannad.common.baserx;

import android.support.annotation.NonNull;

import com.clannad.common.commonutils.LogUtils;
import com.zhy.autolayout.utils.L;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by F_ck on 2016/10/10.
 * 用RxJava实现的EventBus
 */

public class RxBus {
    private static RxBus instance;

    public static synchronized RxBus getInstance() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    private RxBus() {
    }

    /**
     * SuppressWarnings压制警告，即去除警告
     * rawtypes是说传参时也要传递带泛型的参数
     */
    @SuppressWarnings("rawtypes")
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    /**
     * 订阅事件源
     */
    public RxBus OnEvent(Observable<?> mObservable, Action1<Object> mAction1) {
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(mAction1, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
        return getInstance();
    }

    /**
     * 注册事件源
     */
    @SuppressWarnings("rawtypes")
    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<Subject>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        LogUtils.logd("register" + tag + "  size:" + subjectList.size());
        return subject;
    }

    @SuppressWarnings("rawtypes")
    public void unregister(@NonNull Object tag) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjectMapper.remove(tag);
        }
    }

    /**
     * 取消监听
     */
    public RxBus unregister(@NonNull Object tag,
                            @NonNull Observable<?> observable) {
        if (null == observable)
            return getInstance();
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag);
                LogUtils.logd("unregister" + tag + "    size:" + subjects.size());
            }
        }
        return getInstance();
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    /**
     * 触发事件
     */
    @SuppressWarnings("rawtypes")
    public void post(@NonNull Object tag,@NonNull Object content){
        LogUtils.logd("post" + "    eventName:" + tag);
        List<Subject> subjects = subjectMapper.get(tag);
        if(!isEmpty(subjects)){
            for(Subject subject : subjects){
                subject.onNext(content);
                LogUtils.logd("OnEvent  eventName: " + tag);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }

}
