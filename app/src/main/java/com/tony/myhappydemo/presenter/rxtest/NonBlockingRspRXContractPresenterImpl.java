package com.tony.myhappydemo.presenter.rxtest;

import com.almin.horimvplibrary.contract.AbstractContract;
import com.tony.myhappydemo.contract.NonBlockingRspRXContract;
import com.tony.myhappydemo.ui.rxtest.NonBlockingRspRXActivity;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by Tony on 2018/7/2
 * description: 说明
 * Copyright: Copyright (c) Hori All right reserved
 * version:
 */
public class NonBlockingRspRXContractPresenterImpl implements NonBlockingRspRXContract.Presenter {

    private static final String TAG = NonBlockingRspRXContractPresenterImpl.class.getSimpleName();

    private NonBlockingRspRXContract.ViewRenderer mViewRenderer;
    private NonBlockingRspRXContract.DataSource mDataSource;

    private NonBlockingRspRXActivity mContext;

    public NonBlockingRspRXContractPresenterImpl(NonBlockingRspRXActivity context, NonBlockingRspRXContract.ViewRenderer viewRenderer, NonBlockingRspRXContract.DataSource dataSource) {
        this.mViewRenderer = viewRenderer;
        this.mDataSource   = dataSource;
        this.mContext = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {

    }
}
