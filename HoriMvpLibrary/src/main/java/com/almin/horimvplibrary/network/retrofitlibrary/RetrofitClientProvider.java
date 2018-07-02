package com.almin.horimvplibrary.network.retrofitlibrary;

import android.support.annotation.CallSuper;


import com.almin.horimvplibrary.network.TokenOperator;
import com.almin.horimvplibrary.network.TokenProxyHandler;
import com.almin.horimvplibrary.network.retrofitlibrary.errorhandlecomponent.RxErrorHandlingCallAdapterFactory;
import com.almin.horimvplibrary.network.retrofitlibrary.interceptor.ConnectivityInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Almin on 2017/12/25.
 * api请求工具基类，新项目请继承次类新建一个单例，并且实现基类方法；如果需要开启token过期自动刷新重发请求功能需要实现一个tokenproxyhandler
 */

public abstract class RetrofitClientProvider {
    private RetrofitConfiguration mRetrofitConfiguration;
    private Retrofit mRetrofit;
    private TokenOperator mTokenOperator;


    public void init(RetrofitConfiguration retrofitConfiguration){
        this.mRetrofitConfiguration = retrofitConfiguration;
        mTokenOperator = initTokenOperator();
        initRetrofit();
        initService();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(getGsonConverter())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(getBaseUrl())
                .build();
    }

    protected abstract String getBaseUrl();

    protected abstract void initService();

    @CallSuper
    protected void initHttpClientConfig(OkHttpClient.Builder builder){
        builder.connectTimeout(mRetrofitConfiguration.getConnectTimeout(), TimeUnit.MILLISECONDS);
        builder.readTimeout(mRetrofitConfiguration.getConnectTimeout(), TimeUnit.MILLISECONDS);
    }

    @CallSuper
    protected void addInterceptor(OkHttpClient.Builder builder){
        builder.addInterceptor(new ConnectivityInterceptor(mRetrofitConfiguration));
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        initHttpClientConfig(builder);
        addInterceptor(builder);

//        if(retrofitConfiguration.getServicesHost().contains("localhost")) {
//            builder.hostnameVerifier((s, sslSession) -> true);
//        }
        return builder.build();
    }


    @SuppressWarnings("unchecked")
    protected   <T> T createService(Class<T> clazz) {
        T t = getRetrofit().create(clazz);
        if(mTokenOperator==null){
            return t;
        }
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] { clazz }, new TokenProxyHandler(t,mTokenOperator));
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static RequestBody createCustomJsonRequestBody(String json){
        return RequestBody.create(MediaType.parse("application/json"), json);
    }

    protected Converter.Factory getGsonConverter() {
        return GsonConverterFactory.create();
    }

    protected RetrofitConfiguration getConfiguration(){
        return mRetrofitConfiguration;
    }

    protected TokenOperator initTokenOperator(){
        return null;
    }
}
