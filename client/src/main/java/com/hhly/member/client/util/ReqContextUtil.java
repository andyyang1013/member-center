package com.hhly.member.client.util;


import lombok.Data;

/**
* 使用ThreadLocal封装每次请求的一些参数
* @author jiasx
* @create 2017/11/30 12:57
**/
public class ReqContextUtil {

    /**
    * 用户请求信息上下文
    **/
    private static ThreadLocal<ReqContext> reqContextLocal = new ThreadLocal<>();
	

    /**
     * 设置请求/响应token
     */
    public static void setToken(String token){
        ReqContext reqContext = null;
        if(reqContextLocal.get()==null){
            reqContext = new ReqContext();
            reqContextLocal.set(reqContext);
        }else{
            reqContext = reqContextLocal.get();
        }
        reqContext.setToken(token);
    }

    /**
     * 设置请求/响应cookie
     */
    public static void setCookie(String cookie){
        ReqContext reqContext = null;
        if(reqContextLocal.get()==null){
            reqContext = new ReqContext();
            reqContextLocal.set(reqContext);
        }else{
            reqContext = reqContextLocal.get();
        }
        reqContext.setCookie(cookie);
    }


    /**
     * 获取请求/响应token
     */
    public static String getToken(){
        if(reqContextLocal.get()==null){
            return null;
        }
    	return reqContextLocal.get().getToken();
    }


    /**
     * 获取请求/响应cookie
     */
    public static String getCookie(){
        if(reqContextLocal.get()==null){
            return null;
        }
    	return reqContextLocal.get().getCookie();
    }


    /**
     * 清除当前线程保留的内容
     */
    public static void remove(){
    	reqContextLocal.remove();
    }

    /**
     * 用户请求对象
     * @author jiasx
     */
    @Data
    public static class ReqContext {

        /**
         * 请求/响应token
         **/
        private String token;

        /**
         * 请求/响应的cookie
         **/
        private String cookie;

    }
}
