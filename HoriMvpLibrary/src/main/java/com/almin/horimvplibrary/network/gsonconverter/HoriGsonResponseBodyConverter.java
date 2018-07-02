package com.almin.horimvplibrary.network.gsonconverter;

import android.util.Log;

import com.almin.horimvplibrary.network.model.HttpStatus;
import com.almin.horimvplibrary.network.retrofitlibrary.errorhandlecomponent.RetrofitException;
import com.almin.horimvplibrary.network.retrofitlibrary.errorhandlecomponent.TokenInvalidException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * Created by Almin on 2018/1/10.
 */
public class HoriGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
  private final Gson gson;
  private final TypeAdapter<T> adapter;

  HoriGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    String response = value.string();
    Log.v("Hori api", "response:" + response);
    HttpStatus httpStatus = gson.fromJson(response, HttpStatus.class);

    if (!httpStatus.isSuccess()) {
      value.close();
      if(httpStatus.isTokenInvalid()){
        throw new TokenInvalidException();
      }else{
        throw RetrofitException.noConnectivityError(new IOException(httpStatus.getReason()));
      }
    }

    MediaType contentType = value.contentType();
    Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
    InputStream inputStream = new ByteArrayInputStream(response.getBytes());
    Reader reader = new InputStreamReader(inputStream, charset);
    JsonReader jsonReader = gson.newJsonReader(reader);

    try {
      return adapter.read(jsonReader);
    } finally {
      value.close();
    }
  }
}
