package com.almin.horimvplibrary.network.model;

import com.google.gson.annotations.SerializedName;

public class HttpStatus {
    @SerializedName("result")
    private String result;
    @SerializedName("reason")
    private String reason;

    private String code;
    private String token;
    private String validCode;  //为方便测试，这是后台返回的验证码。


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */


    public boolean isSuccess() {
        return "0".equals(result);
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getCode() {
        return code;
    }

    public int getCodeInt() {
        try {
            return Integer.parseInt(code);
        } catch (Exception e) {
        }
        return -1;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResponseJson{" +
                "result='" + result + '\'' +
                ", reason='" + reason + '\'' +
                ", code='" + code + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public boolean isTokenInvalid(){
        return "002".equals(result) || "003".equals(result);
    }
}