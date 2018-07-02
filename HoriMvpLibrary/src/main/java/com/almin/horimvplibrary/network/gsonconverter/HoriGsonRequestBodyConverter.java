package com.almin.horimvplibrary.network.gsonconverter;

import android.util.Log;

import com.almin.horimvplibrary.network.model.HoriRequest;
import com.almin.horimvplibrary.network.model.TokenProvider;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by Almin on 2018/1/10.
 */
public class HoriGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

  private final String TAG = this.getClass().getSimpleName();

  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  private final Gson gson;
  private final TypeAdapter<T> adapter;
  private TokenProvider tokenProvider;

  HoriGsonRequestBodyConverter(TokenProvider tokenProvider, Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public RequestBody convert(T value) throws IOException {
    Buffer buffer = new Buffer();
    Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
    JsonWriter jsonWriter = gson.newJsonWriter(writer);
    adapter.write(jsonWriter, value);
    jsonWriter.close();

    RequestBody requestBody;

    if(value instanceof HoriRequest){
      if(tokenProvider!=null){
        ((HoriRequest)value).updateToken(tokenProvider.getToken());
      }
      requestBody = RequestBody.create(MediaType.parse("application/json"), ((HoriRequest)value).toJson());
      Log.v("Hori api", "request:" + ((HoriRequest)value).toJson());
    }else{
      requestBody = RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
    return requestBody;
  }


}