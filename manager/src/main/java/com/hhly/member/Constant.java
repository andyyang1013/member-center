package com.hhly.member;

/**
* 工程常量类
* @author jiasx
* @create 2017/12/2 16:16
**/
public class Constant {

    /**
     * 项目名称，用于在redis中进行区分
     */
    public static final String PROJ_NAME = "member-center-manager";


    /**
     * http前缀
     */
    public static final String HTTP_PREFIX = "http://";

    /**
     * https前缀
     */
    public static final String HTTPS_PREFIX = "https://";

    /**
     * 用户登录token key
     */
    public static final String USER_TOKEN = "token";


    /**
     * 用户登录token【keyType=string,value=user对象】
     */
    public static final String USER_TOKEN_REDIS_KEY = PROJ_NAME+":user_token:token=%s";
    

    /**2小时*/
    public static final int USER_TOKEN_EXPIRE = 60 * 60 * 2;



}
