package com.almin.horimvplibrary.network.model;

/**
 * Created by Almin on 2018/1/12.
 */

public interface HoriRequest<T> {
    String toJson();
    // 当token过期的时候重新获取token后发请求之前进行更新token
    void updateToken(String token);
}
