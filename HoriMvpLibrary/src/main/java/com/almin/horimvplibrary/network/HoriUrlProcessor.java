package com.almin.horimvplibrary.network;


import com.almin.horimvplibrary.network.retrofitlibrary.ApiConfigProvider;
import com.almin.horimvplibrary.network.retrofitlibrary.urlprocessor.DefaultUrlProcessor;

/**
 * Created by Almin on 2018/1/16.
 */

public class HoriUrlProcessor extends DefaultUrlProcessor {
    private ApiConfigProvider mApiConfigProvider;

    public HoriUrlProcessor(ApiConfigProvider apiConfigProvider){
        mApiConfigProvider = apiConfigProvider;
    }

    @Override
    public String getBaseUrl() {
        return null;
    }


    public String getBaseUrl(String url) {
        return mApiConfigProvider.getApiConfig(obtainBaseUrlKeyFromPath(url));
    }

    private String obtainBaseUrlKeyFromPath(String url) {
        String[] paths = url.split("/");
        return paths.length>1? String.format("%s_server",paths[1]):null;
    }


}
