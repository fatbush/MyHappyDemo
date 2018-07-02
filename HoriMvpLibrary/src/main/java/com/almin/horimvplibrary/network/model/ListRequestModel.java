package com.almin.horimvplibrary.network.model;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almin on 2018/1/10.
 * 根据业务需求的统一json格式的请求model，只要传入body的类型 ， 此为对应处理List<T>
 */

public class ListRequestModel<T> implements HoriRequest<T> {

    public static ListRequestModel create(List<Object> list,String token){
        ListRequestModel listRequestModel = new ListRequestModel();
        listRequestModel.setBody(list);
        HeaderBean headerBean = new HeaderBean();
        headerBean.setTimeStamp(System.currentTimeMillis());
        headerBean.setToken(token);
        listRequestModel.setHeader(headerBean);
        return listRequestModel;
    }

    private List<T> body;
    private HeaderBean header;

    //  当body内容为空的时候，是否保留 空的body字段发送json
    private transient boolean isKeepNullBody;

    private boolean isKeepNullBody() {
        return isKeepNullBody;
    }

    public void setIsKeepNullBody(boolean isKeepNullBody) {
        this.isKeepNullBody = isKeepNullBody;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    @Override
    public void updateToken(String token) {
        if(getHeader()!=null && getHeader().getToken()!=null){
            getHeader().setToken(token);
        }
    }

    public String toJson() {
        Gson gson = new Gson();

        Class clazz;
        if(body==null){
            if(isKeepNullBody){
                body = new ArrayList<>();
            }
            clazz = String.class;
        }else{
            if(body.size()>0){
                clazz = body.get(0).getClass();
            }else{
                clazz = String.class;
            }
        }

        Type objectType = type(ListRequestModel.class, clazz);
        return gson.toJson(this, objectType);
    }

    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
