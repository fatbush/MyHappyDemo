package com.almin.horimvplibrary.network.gsonconverter;

import com.almin.horimvplibrary.network.model.TokenProvider;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * Created by Almin on 2018/1/10.
 */

public class HoriGsonConvertFactory extends Converter.Factory {
    private TokenProvider tokenProvider;

    public static HoriGsonConvertFactory create(TokenProvider tokenProvider) {
        return create(tokenProvider, new Gson());
    }

    public static HoriGsonConvertFactory create() {
        return create(null, new Gson());
    }

    public static HoriGsonConvertFactory create(TokenProvider tokenProvider, Gson gson) {
        return new HoriGsonConvertFactory(tokenProvider,gson);
    }

    private final Gson gson;

    private HoriGsonConvertFactory(TokenProvider tokenProvider, Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new HoriGsonResponseBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new HoriGsonRequestBodyConverter<>(tokenProvider, gson, adapter);
    }
}
