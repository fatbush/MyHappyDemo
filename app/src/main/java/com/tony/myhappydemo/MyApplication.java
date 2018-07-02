package com.tony.myhappydemo;


import android.support.multidex.MultiDexApplication;

import com.tony.myhappydemo.util.NonBlockingSimulationService;

/**
 * Created by Tony on 2018/7/2
 * description: 说明
 * Copyright: Copyright (c) Hori All right reserved
 * version:
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        NonBlockingSimulationService.getInstance().init(mInstance);
    }
}
