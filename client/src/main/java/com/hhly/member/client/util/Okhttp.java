package com.hhly.member.client.util;

import com.hhly.member.client.exception.MemberException;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
* 利用okhttp进行get和post的访问
* @author jiasx
* @create 2018/2/26 15:51
**/
public class Okhttp {

    private  OkHttpClient okClient = null;

    private static class Builder {
        private static final Okhttp INSTANCE = new Okhttp();
    }

    public static final Okhttp builder() {
        return Builder.INSTANCE;
    }

    /**
     * 构造方法
     * @return
     */
    private Okhttp(){
        okhttp3.OkHttpClient.Builder clientBuilder=new okhttp3.OkHttpClient.Builder();
        //连接超时
        clientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        //读取超时
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        //写入超时
        clientBuilder.writeTimeout(60, TimeUnit.SECONDS);
        okClient = clientBuilder.addInterceptor(new HeadersInterceptor()).build();
    }


    /** 
     * 发起get请求 
     *  
     * @param url 
     * @return 
     */  
    public String get(String url) {
        String result = null;  
        Request request = new Request.Builder().url(url).build();
        try {  
            Response response = okClient.newCall(request).execute();
            result = response.body().string();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 发送httppost请求 
     *  
     * @param url 
     * @param data  提交的参数为key=value&key1=value1的形式 
     * @return 
     */  
    public String post(String url, String data) {
        String result = null;  
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html;charset=utf-8"), data);
        Request request = new Request.Builder().url(url).post(requestBody).build();  
        try {  
            Response response = okClient.newCall(request).execute();
            result = response.body().string();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }

    /**
     * 发送httppost请求
     *
     * @param url
     * @param headersParams  请求头参数
     * @param bodyParams  body体
     * @return
     */
    public String post(String url, Map<String, String> headersParams,Map<String, String> bodyParams) {
        String result = null;
        Request request = new Request.Builder().url(url).headers(setHeaders(headersParams)).post(setBody(bodyParams)).build();
        try {
            Response response = okClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            throw new MemberException("IOException异常，请确认网络",e);
        }
        return result;
    }

    /**
     * 设置请求头
     * @param headersParams
     * @return
     */
    private Headers setHeaders(Map<String, String> headersParams){
        Headers headers=null;
        Headers.Builder headersbuilder=new Headers.Builder();
        if(headersParams != null) {
            for(Map.Entry<String,String> entry:headersParams.entrySet()){
                if(StringUtils.isNotEmpty(entry.getValue())){
                    headersbuilder.add(entry.getKey(), entry.getValue());
                }
            }
        }
        headers=headersbuilder.build();
        return headers;
    }

    /**
     * post请求参数
     * @param bodyParams
     * @return
     */
    private RequestBody setBody(Map<String, String> bodyParams){
        RequestBody body=null;
        FormBody.Builder formEncodingBuilder=new FormBody.Builder();
        if(bodyParams != null){
            for(Map.Entry<String,String> entry:bodyParams.entrySet()){
                if(StringUtils.isNotEmpty(entry.getValue())) {
                    formEncodingBuilder.add(entry.getKey(), entry.getValue());
                }
            }
        }
        body=formEncodingBuilder.build();
        return body;

    }


    /**
    * 请求头处理拦截器
    * @author jiasx
    * @create 2018/3/7 17:00
    **/
    public class HeadersInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            Response response = chain.proceed(request);

            String cookie = response.header("Set-Cookie");
            ReqContextUtil.setCookie(cookie);
            ReqContextUtil.setToken(getToken(cookie));
            return response;
        }

        /**
         * 获取cookie中封装的token值
         * @param cookie 格式如：token=KWVZOVJrF6MvkB23B10eGg==; Max-Age=7200; Expires=Wed, 07-Mar-2018 11:25:42 GMT; Path=/
         * @return
         */
        private String getToken(String cookie){
            if(StringUtils.isEmpty(cookie)){
                return null;
            }
            String[] cookieArr = cookie.split(";");
            return cookieArr[0].substring(cookieArr[0].indexOf("=")+1);
        }
    }



}  