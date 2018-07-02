package com.tony.myhappydemo.ui.rxtest;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.almin.horimvplibrary.contract.AbstractContract;
import com.tony.myhappydemo.R;
import com.tony.myhappydemo.contract.NonBlockingRspRXContract;
import com.tony.myhappydemo.datasource.NonBlockingRspRXSourceImpl;
import com.tony.myhappydemo.presenter.rxtest.NonBlockingRspRXContractPresenterImpl;
import com.tony.myhappydemo.ui.base.AbstractHoriActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tony on 2018/7/2
 * description: 非阻塞响应测试
 * Copyright: Copyright (c) Hori All right reserved
 * version:
 */
public class NonBlockingRspRXActivity extends AbstractHoriActivity implements NonBlockingRspRXContract.ViewRenderer {

    @BindView(R.id.tv_monitor)
    TextView tvMonitor;
    @BindView(R.id.btn_send)
    Button btnSend;

    private NonBlockingRspRXContractPresenterImpl mPresenter;

    @Override
    protected int getHoriMenuLayout() {
        return 0;
    }

    @Override
    public String getPageTitle() {
        return "非阻塞响应测试";
    }

    @Override
    protected int getLeftNavIconRes() {
        return 0;
    }

    @Override
    protected AbstractContract.Presenter createPresenter() {
        mPresenter = new NonBlockingRspRXContractPresenterImpl(this, this, new NonBlockingRspRXSourceImpl(this.getApplicationContext()));
        return mPresenter;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_non_blocking_rsp_rx_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
    }
}
