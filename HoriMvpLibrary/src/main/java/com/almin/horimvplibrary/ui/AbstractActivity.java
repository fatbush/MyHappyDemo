package com.almin.horimvplibrary.ui;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.almin.horimvplibrary.contract.AbstractContract;
import com.almin.horimvplibrary.widget.ProgressSpinner;


/**
 * Created by Almin on 2018/1/3.
 */

public abstract class AbstractActivity extends LifecycleActivity implements AbstractContract.ViewRenderer {
    private AbstractContract.Presenter mPresenter;

    protected abstract AbstractContract.Presenter createPresenter();

    protected ProgressSpinner progressSpinner;

    protected abstract int getContentViewResource();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(getActivityDefaultOri());
        bindPresenter();
    }

    /**
     * 默认方向配置
     *
     * @return
     */
    protected int getActivityDefaultOri() {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @CallSuper
    @SuppressWarnings("unchecked")
    protected final void bindPresenter() {
        mPresenter = createPresenter();
        if (mPresenter == null) {
            throw new IllegalArgumentException("Presenter can not be null!");
        }
    }

    @CallSuper
    protected void unBindPresenter() {
        mPresenter.detach();
    }

    //for abstract ViewRenderer method
    public void goBack() {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        unBindPresenter();
        super.onDestroy();
        hideSpinner();
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnackBar(String msg) {
    }

    @Override
    public void showSpinner() {
        if(progressSpinner!=null && !progressSpinner.isShowing()){
            progressSpinner.show();
        }
    }


    @Override
    public void hideSpinner() {
        if(progressSpinner!=null){
            progressSpinner.hide();
        }
    }
}
