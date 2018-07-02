package com.almin.horimvplibrary.network.retrofitlibrary;


/**
 * Created by Almin on 2017/12/12.
 */

public interface RetrofitConfiguration {
    long getConnectTimeout();
    String getServicesHost();
    boolean isNetworkAvailable();
}
