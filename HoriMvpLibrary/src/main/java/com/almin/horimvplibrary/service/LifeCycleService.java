package com.almin.horimvplibrary.service;

import android.app.Service;
import android.support.annotation.CallSuper;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Almin on 2018/1/10.
 * 生命周期检测的基类
 */

public abstract class LifeCycleService extends Service implements LifecycleProvider<ActivityEvent> {

    private final PublishSubject<ActivityEvent> mLifecycleSubject = PublishSubject.create();


    @Override
    public Observable<ActivityEvent> lifecycle() {
        return mLifecycleSubject.hide();
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(mLifecycleSubject, event);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return null;
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mLifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }

}
