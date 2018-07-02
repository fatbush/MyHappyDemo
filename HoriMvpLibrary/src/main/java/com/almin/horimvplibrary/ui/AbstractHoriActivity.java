package com.almin.horimvplibrary.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.almin.horimvplibrary.widget.ProgressSpinner;


/**
 * Created by Almin on 2017/12/29.
 * 此类主要为编写一些规范名称的钩子函数作为限制，新项目继承此类后主要编写页面公共ui层与业务层代码，请在onHoriXXXX 下写代码
 */

public abstract class AbstractHoriActivity extends AbstractActivity {

    @Override
    protected final void onStart() {
        super.onStart();
        onHoriStart();
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onHoriCreate(savedInstanceState);
        progressSpinner = getProgressSpinner();
        initView();
        initData();
    }

    @CallSuper
    public void onHoriCreate(@Nullable Bundle savedInstanceState) {

    }

    // 重写该方法为 左侧栏导航键点击事件，默认后退和后退图标
    protected void onLeftNavClick() {
        onBackPressed();
    }

    @Override
    protected final void onResume() {
        super.onResume();
        onHoriResume();
    }

    @Override
    protected final void onPause() {
        onHoriPause();
        super.onPause();
    }

    @Override
    protected final void onDestroy() {
        onHoriDestroy();
        super.onDestroy();
    }

    @CallSuper
    protected void onHoriStart() {
    }

    @CallSuper
    protected void onHoriResume() {
    }

    @CallSuper
    protected void onHoriPause() {
    }

    @CallSuper
    protected void onHoriDestroy() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuLayout = getHoriMenuLayout();
        if (menuLayout > 0) {
            getMenuInflater().inflate(menuLayout, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 提供右侧菜单能力
     *
     * @return
     */
    protected abstract int getHoriMenuLayout();

    /**
     * 设置初始状态栏的颜色，默认是透明度是50%
     *
     * @return @ColorRes 颜色资源
     */
    protected int getHoriStatusbarColor() {
        return android.R.color.white;
    }

    /**
     * 设置标题栏的名称
     */
    public abstract String getPageTitle();

    /**
     * 设置左上角导航栏按键图标
     */
    protected abstract int getLeftNavIconRes();

    /**
     * 初始化loading view
     */
    protected abstract ProgressSpinner getProgressSpinner();
}


