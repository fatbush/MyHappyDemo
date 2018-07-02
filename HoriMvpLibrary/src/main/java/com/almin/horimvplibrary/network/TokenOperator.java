package com.almin.horimvplibrary.network;

import retrofit2.Response;

public interface TokenOperator{
    void updateToken(String token);
    long getLastUpdateTokenTime();
    void updateUpdateTokenLastTime(long updateTokenLastTime);
    Response<String> callRefreshUserTokenApi();

}