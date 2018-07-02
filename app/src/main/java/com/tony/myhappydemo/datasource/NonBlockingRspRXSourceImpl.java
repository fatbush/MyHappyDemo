package com.tony.myhappydemo.datasource;

import android.content.Context;

import com.tony.myhappydemo.contract.NonBlockingRspRXContract;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by Tony on 2018/7/2
 * description: 说明
 * Copyright: Copyright (c) Hori All right reserved
 * version:
 */
public class NonBlockingRspRXSourceImpl implements NonBlockingRspRXContract.DataSource {

    Context mContext;

    public NonBlockingRspRXSourceImpl(Context context) {
        mContext = context;
    }

    @Override
    public void unSubscribeSubjects() {

    }

    @Override
    public void searchALLAutoUploadRecordList() {

    }
}
