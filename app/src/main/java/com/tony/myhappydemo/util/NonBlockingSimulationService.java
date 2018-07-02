package com.tony.myhappydemo.util;

import android.content.Context;
import android.os.Handler;

/**
 * Created by Tony on 2018/7/2
 * description: 模拟非阻塞响应
 * Copyright: Copyright (c) Hori All right reserved
 * version:
 */
public class NonBlockingSimulationService extends Handler {

    private Context mContext;

    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    public static NonBlockingSimulationService getInstance(){
        return InstanceHolder.sBaiduStatisticsController;
    }

    public static class InstanceHolder{
        public static NonBlockingSimulationService sBaiduStatisticsController = new NonBlockingSimulationService();
    }
}
