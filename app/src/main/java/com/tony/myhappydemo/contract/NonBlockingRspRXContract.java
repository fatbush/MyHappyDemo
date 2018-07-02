package com.tony.myhappydemo.contract;

import com.almin.horimvplibrary.contract.AbstractContract;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by Tony on 2018/7/2
 * description: 说明
 * Copyright: Copyright (c) Hori All right reserved
 * version:
 */
public class NonBlockingRspRXContract {

    /**view层控制接口 */
    public interface ViewRenderer extends AbstractContract.ViewRenderer{

    }

    /**数据持久化层接口 */
    public interface DataSource extends AbstractContract.DataSource{
        void searchALLAutoUploadRecordList();
    }

    /**业务层控制接口*/
    public interface Presenter extends AbstractContract.Presenter{

    }
}
