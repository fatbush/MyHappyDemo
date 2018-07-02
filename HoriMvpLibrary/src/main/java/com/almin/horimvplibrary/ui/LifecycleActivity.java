package com.almin.horimvplibrary.ui;

import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import com.almin.horimvplibrary.network.retrofitlibrary.RxSchedulerHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Almin on 2017/12/28.
 */

public abstract class LifecycleActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent> {

    private final PublishSubject<ActivityEvent> mLifecycleSubject = PublishSubject.create();
    private List<Disposable> mDisposables = new ArrayList<>();

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
    protected void onDestroy() {
        mLifecycleSubject.onNext(ActivityEvent.DESTROY);
        unSubscribeSubjects();
        super.onDestroy();
    }

    @CallSuper
    @Override
    protected void onStop() {
        mLifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }


    protected final void subscribeSubject(Disposable disposable){
        if(!mDisposables.contains(disposable)){
            mDisposables.add(disposable);
        }
    }

    private void unSubscribeSubjects(){
        for(Disposable disposable : mDisposables){
            disposable.dispose();
        }
        mDisposables.clear();
    }

    protected ObservableTransformer getDestroyComposer() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    @SuppressWarnings("unchecked")
    protected <T> ObservableTransformer<T, T> getCommonApiComposer() {
        return upstream ->
                upstream.compose(RxSchedulerHelper.io_main())
                        .compose(getDestroyComposer());
    }
}
