package com.almin.horimvplibrary.network.retrofitlibrary.errorhandlecomponent;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(){
        super("token is invalid.");
    }
}