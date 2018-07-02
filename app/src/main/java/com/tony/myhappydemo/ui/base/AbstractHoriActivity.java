package com.tony.myhappydemo.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.almin.horimvplibrary.widget.ProgressSpinner;
import com.tony.myhappydemo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Almin on 2017/12/29.
 * 应用ui层基本逻辑，mvplibrary 不含相对应app的基础ui
 */

public abstract class AbstractHoriActivity extends com.almin.horimvplibrary.ui.AbstractHoriActivity {
    public static final String DEFAULT_FRAGMENT_TAG = "real_content";

    private ProgressDialog mDialog;
    private Unbinder mBind;

    protected boolean isAutoFitImmersionBar() {
        return true;
    }


    @CallSuper
    public void onHoriCreate(@Nullable Bundle savedInstanceState) {
        super.onHoriCreate(savedInstanceState);
        if (getContentViewResource() > 0) {
            setContentView(getContentViewResource());
        }
        mBind = ButterKnife.bind(this);
    }


    // 重写控制加载中view样式
    @Override
    protected ProgressSpinner getProgressSpinner() {
        return new ProgressSpinner() {
            @Override
            public void show() {
                showProgress("加载中");
            }

            @Override
            public void hide() {
                hidProgress();
            }

            @Override
            public boolean isShowing() {
                return AbstractHoriActivity.this.isShowing();
            }
        };
    }

    /**
     * 显示一个加载中的loading对话框progressDialog
     *
     * @param message
     */
    protected void showProgress(String message) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = new ProgressDialog(this);
        mDialog.setCancelable(true);
        try {
            mDialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }


    protected boolean isShowing() {
        if (mDialog == null || !mDialog.isShowing()) return false;
        return true;
    }

    /**
     * 不能取消的进度对话框
     *
     * @param message
     * @param cancelable
     */
    protected void showProgress(String message, boolean cancelable) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = new ProgressDialog(this);
        mDialog.setCancelable(cancelable);
        try {
            mDialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭progressDialog
     */
    protected void hidProgress() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @CallSuper
    protected void onHoriDestroy() {
        super.onHoriDestroy();
        mBind.unbind();
    }


    /**
     * 设置初始状态栏的颜色，默认是透明度是50%
     *
     * @return @ColorRes 颜色资源
     */
    protected int getHoriStatusbarColor() {
        return android.R.color.white;
    }

    /**
     * 配合状态栏颜色使用，如果是浅色尝试用深色字体
     *
     * @return
     */
    protected boolean ifNeedHoriStatusbarFontDark() {
        return true;
    }

    public Fragment getHoriContentFragment() {
        return null;
    }

    private int getRootContentViewResource() {
        return R.layout.activity_hori_mvp_back;
    }

    /**
     * 设置Toolbar的背景
     */
    protected int getToolbarBgRes() {
        return 0;
    }

    /**
     * 设置标题线颜色
     *
     * @return
     */
    protected int getTitleLineColor() {
        return 0;
    }


}


