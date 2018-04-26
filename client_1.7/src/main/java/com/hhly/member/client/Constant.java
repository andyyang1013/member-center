package com.hhly.member.client;/**
 * Created by dell on 2018/2/26.
 */

/**
 * 常量类
 *
 * @author jiasx
 * @create 2018-02-26 18:44
 **/
public class Constant {

    /**
     * 请求成功的编码
     */
    public static final String SUCCESS_CODE = "200";

    /**
     * 域名或者ip地址
     */
//    public static String BASE_DOMAIN = "http://10.41.12.85:9902";
//    public static String BASE_DOMAIN = "http://192.168.74.162:9902";
    public static String BASE_DOMAIN = "http://umcapi.zcopain.com";


    /**
     * 基础url地址
     */
    public static String BASE_URL = BASE_DOMAIN+"/member";

    /**
     * 当前公司分配的apiKey,可以根据线上信息修改
     */
    public static String API_KEY = "e1a2c53d16666ece99cd63f669350e5e";

    /**
     * 当前公司分配的apiSecret,可以根据线上信息修改
     */
    public static String API_SECRET = "2a370730be48489b9633c362ba5ea756";

    /**
     * 可以用账号/邮箱/手机号登录的地址
     */
    public static String LOGIN_URL = BASE_URL+"/api/login";
    
    /**
     * 可以用账号/邮箱/手机号查询会员信息的地址
     */
    public static String QUERY_URL = BASE_URL+"/api/queryUserInfo";

    /**
     * 可以用子公司会员id和编号查询会员信息的地址
     */
    public static String QUERY_SUB_URL = BASE_URL+"/api/queryUserInfoBySubCodeAndSubUserId";

    /**
     * 批量查询用户信息地址
     */
    public static String BATCH_QUERY_URL = BASE_URL+"/api/batchQueryUserInfo";

    /**
     * 可以用账号/邮箱/手机号查询会员是否存在的地址
     */
    public static String EXSIST_URL = BASE_URL+"/api/exsistUser";

    /**
     * 针对有些子公司对邮箱、手机登录有特殊控制的公司，需要自己识别用什么方式登录。
     */
    public static String LOGIN_DIFF_URL = BASE_URL+"/api/loginDiff";

    /**
     * 验证码免密登录
     */
    public static String LOGIN_WITH_VERIFY_CODE_URL = BASE_URL+"/api/loginWithVerifyCode";

    /**
     * 登出
     */
    public static String LOGINOUT_URL = BASE_URL+"/api/loginOut";

    /**
     * 检查登录状态，需要传递token
     */
    public static String CHECK_LOGIN_STATUS_URL = BASE_URL+"/api/checkLoginStatus";

    /**
     * 修改token失效时间，需要传递token
     */
    public static String MODIFY_TOKEN_EXPIRE_TIME_URL = BASE_URL+"/api/modifyTokenExpireTime";

    /**
     * 注册
     */
    public static String REGISTER_URL = BASE_URL+"/api/register";

    /**
     * 注册确认
     */
    public static String REGISTER_CONFIRM_URL = BASE_URL+"/api/registerConfirm";

    /**
     * 修改个人资料
     * 可修改除账号外的所有信息，新的手机号和邮箱号唯一     *
     */
    public static String UPDATE_USER_URL = BASE_URL + "/api/userinfo/update";

    /**
     * 账号绑定接口
     */
    public static String ACCOUNTS_BIND_URL = BASE_URL + "/api/accounts/bind";

    /**
     * 重置密码
     */
    public static String ACCOUNT_RESET_PASSWORD = BASE_URL + "/api/account/resetPassword";

    /**
     * 修改密码
     */
    public static String ACCOUNT_MODIFY_PASSWORD = BASE_URL + "/api/account/modifyPassword";

    /**
     * 设置环境信息，包括域名或者ip、apiKey，apiSecret
     * @param domain
     * @param apiKey
     * @param apiSecret
     * @return
     */
    public static void setEnvInfo(String domain,String apiKey,String apiSecret){

        API_KEY = apiKey;

        API_SECRET = apiSecret;

        BASE_DOMAIN = domain;

        BASE_URL = BASE_DOMAIN+"/member";

        LOGIN_URL = BASE_URL+"/api/login";

        QUERY_URL = BASE_URL+"/api/queryUserInfo";

        QUERY_SUB_URL = BASE_URL+"/api/queryUserInfoBySubCodeAndSubUserId";

        BATCH_QUERY_URL = BASE_URL+"/api/batchQueryUserInfo";

        EXSIST_URL = BASE_URL+"/api/exsistUser";

        LOGIN_DIFF_URL = BASE_URL+"/api/loginDiff";

        LOGIN_WITH_VERIFY_CODE_URL = BASE_URL+"/api/loginWithVerifyCode";

        LOGINOUT_URL = BASE_URL+"/api/loginOut";

        CHECK_LOGIN_STATUS_URL = BASE_URL+"/api/checkLoginStatus";

        MODIFY_TOKEN_EXPIRE_TIME_URL = BASE_URL+"/api/modifyTokenExpireTime";

        REGISTER_URL = BASE_URL+"/api/register";

        REGISTER_CONFIRM_URL = BASE_URL+"/api/registerConfirm";

        UPDATE_USER_URL = BASE_URL + "/api/userinfo/update";

        ACCOUNTS_BIND_URL = BASE_URL + "/api/accounts/bind";

        ACCOUNT_RESET_PASSWORD = BASE_URL + "/api/account/resetPassword";

        ACCOUNT_MODIFY_PASSWORD = BASE_URL + "/api/account/modifyPassword";
    }

}
