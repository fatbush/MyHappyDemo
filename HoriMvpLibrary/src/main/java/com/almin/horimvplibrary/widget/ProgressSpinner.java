package com.almin.horimvplibrary.widget;

/**
 * Created by Almin on 2018/5/16.
 * 加载进度view 基本接口，用于基类隔离自定义ProgressDialog具体实现
 */

public interface ProgressSpinner {
    void show();
    void hide();
    boolean isShowing();
}
