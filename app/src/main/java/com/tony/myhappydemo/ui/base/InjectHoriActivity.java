package com.tony.myhappydemo.ui.base;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Tony on 2018/6/22
 * description: 带EventBus的注册
 * Copyright: Copyright (c) Hori All right reserved
 * version:
 */
public abstract class InjectHoriActivity extends AbstractHoriActivity {
    @Override
    protected void onHoriResume() {
        super.onHoriResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onHoriPause() {
        super.onHoriPause();
        EventBus.getDefault().unregister(this);
    }
}
