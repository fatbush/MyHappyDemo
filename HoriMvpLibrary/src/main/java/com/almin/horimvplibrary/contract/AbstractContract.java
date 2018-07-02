package com.almin.horimvplibrary.contract;


/**
 * Created by almin on 2017/12/7.
 *
 *  禁止 传入Context 到Presenter 以及Datasource 层
 */

public interface AbstractContract {

    interface ViewRenderer{
        void showSpinner(); // 显示loading 进度旋转条
        void hideSpinner();
        void goBack();
        void showToast(String msg);
        void showSnackBar(String msg);
    }

    interface Presenter{
        void start();  //做页面第一次初始化动作的所有内容
        void detach(); //销毁一些presenter里面用到的list数据集合等缓存

        // for simple ui not need presenter
        Presenter EMPTY = new Presenter() {
            @Override
            public void start() {
            }

            @Override
            public void detach() {
            }
        };
    }


    interface DataSource{
        //   saveToRP /getFromRP (Repository: DateBase, Manager)
        //   saveToSP /getFromSP (SharedPreferences)

        void unSubscribeSubjects();
    }

}
