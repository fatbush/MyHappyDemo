package com.almin.horimvplibrary.network.model;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Almin on 2018/1/10.
 * 根据业务需求的统一json格式的请求model，只要传入body的类型 ， 此为对应处理单个对象T ，最底下有测试例子代码
 */

public class RequestModel<T> implements HoriRequest<T> {

    public static RequestModel create(Object obj,String token){
        RequestModel requestModel = new RequestModel();
        requestModel.setBody(obj);
        HeaderBean headerBean = new HeaderBean();
        headerBean.setTimeStamp(System.currentTimeMillis());
        headerBean.setToken(token);
        requestModel.setHeader(headerBean);
        return requestModel;
    }

    public static RequestModel create(Object obj){
        RequestModel requestModel = new RequestModel();
        requestModel.setBody(obj);
        HeaderBean headerBean = new HeaderBean();
        headerBean.setTimeStamp(System.currentTimeMillis());
        headerBean.setToken(null);
        requestModel.setHeader(headerBean);
        return requestModel;
    }

    private T body;
    private HeaderBean header;

    //  当body内容为空的时候，是否保留 空的body字段发送json
    private transient boolean isKeepNullBody;

    //  是否需要token过期后自动更新最新token
    private transient boolean isNeedAutoRefreshToken = true;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    private boolean isKeepNullBody() {
        return isKeepNullBody;
    }

    public void setIsKeepNullBody(boolean isKeepNullBody) {
        this.isKeepNullBody = isKeepNullBody;
    }

    public void setNeedAutoRefreshToken(boolean needAutoRefreshToken) {
        this.isNeedAutoRefreshToken = needAutoRefreshToken;
    }

    public String toJson() {
        Gson gson = new Gson();

        Class clazz;
        if(body==null){
            if(isKeepNullBody){
                body = (T) "";
            }
            clazz = String.class;
        }else{
            clazz = body.getClass();
        }

        Type objectType = type(RequestModel.class, clazz);
        return gson.toJson(this, objectType);
    }

    @Override
    public void updateToken(String token) {
        if(isNeedAutoRefreshToken && getHeader()!=null && getHeader().getToken()!=null){
            getHeader().setToken(token);
        }
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


//
//    public static void main(String[] args) {
//        WEE weee = new WEE();
//        weee.setText("12121212");
//        RequestModel<WEE> requestModel = new RequestModel<>();
//        requestModel.setBody(weee);
//        HeaderBean headerBean = new HeaderBean();
//        headerBean.setToken("121212323");
//        headerBean.setTimeStamp(5445949);
//        requestModel.setHeader(headerBean);
//
//        System.out.println(" json   "+requestModel.toJson());

//        List<WEE> list = new ArrayList<>();

//        WEE wee = new WEE();
//        wee.setText("1111");
//
//        WEE wee1 = new WEE();
//        wee1.setText("2222");

//        list.add(wee);
//        list.add(wee1);

//        ListRequestModel<WEE> requestModel2 = new ListRequestModel<>();
//        requestModel2.setBody(list);
//        HeaderBean headerBean2 = new HeaderBean();
//        headerBean2.setToken("121212323");
//        headerBean2.setTimeStamp(5445949);
//        requestModel2.setHeader(headerBean2);
//
//        System.out.println(" json   "+requestModel2.toJson());

//        RequestModel requestModel = new RequestModel<>();
//        requestModel.setIsKeepNullBody(true);
//        HeaderBean headerBean = new HeaderBean();
//        headerBean.setToken("121212323");
//        headerBean.setTimeStamp(5445949);
//        requestModel.setHeader(headerBean);
//
//        System.out.println(" json   "+requestModel.toJson());

//        ListRequestModel requestModel2 = new ListRequestModel<>();
//        HeaderBean headerBean2 = new HeaderBean();
//        headerBean2.setToken("121212323");
//        headerBean2.setTimeStamp(5445949);
//        requestModel2.setHeader(headerBean2);
//        requestModel2.setIsKeepNullBody(true);
//        System.out.println(" json   "+requestModel2.toJson());

//    }
//
//    private static class WEE{
//        private String text;
//
//        public String getText() {
//            return text;
//        }
//
//        public void setText(String text) {
//            this.text = text;
//        }
//    }
}
