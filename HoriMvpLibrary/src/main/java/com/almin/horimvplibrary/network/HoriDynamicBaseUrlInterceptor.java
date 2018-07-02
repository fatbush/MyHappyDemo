package com.almin.horimvplibrary.network;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Almin on 2018/1/16.
 */

public class HoriDynamicBaseUrlInterceptor implements Interceptor {
    private HoriUrlProcessor mHoriUrlProcessor;

    public HoriDynamicBaseUrlInterceptor(HoriUrlProcessor horiUrlProcessor){
        mHoriUrlProcessor = horiUrlProcessor;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        String baseUrl = mHoriUrlProcessor.getBaseUrl(request.url().encodedPath());
        System.out.println("  base url changed :   "+baseUrl);
        HttpUrl baseHttpUrl = HttpUrl.parse(baseUrl);

        Log.d("Http request", String.format("Request http url base url key is ：%s ",baseUrl));


        mHoriUrlProcessor.addHeader(requestBuilder);

        if (null == baseHttpUrl) {
            throw new InvalidUrlException(baseUrl);
        }

        HttpUrl requestHttpUrl = mHoriUrlProcessor.wrapUrl(baseHttpUrl,request.url());
        if(requestHttpUrl != null){
            requestBuilder.url(requestHttpUrl);
            Log.d("Http request", String.format("Request http url is ： %s",requestHttpUrl.toString()));
        }

        return chain.proceed(requestBuilder.build());
    }

    private static class InvalidUrlException extends RuntimeException {

        private InvalidUrlException(String url) {
            super("You've configured an invalid url : " + (TextUtils.isEmpty(url) ? "EMPTY_OR_NULL_URL" : url));
        }
    }
}
