package com.hhly.member.config.filter.shiro;

import com.hhly.member.Constant;
import com.hhly.member.config.filter.UserReqContextUtil;
import com.hhly.member.exception.BizException;
import com.hhly.member.exception.CodeMsg;
import com.hhly.member.repository.IRedisRepository;
import com.hhly.member.util.CookieUtil;
import com.hhly.member.util.JacksonUtil;
import com.hhly.member.vo.ResponseT;
import com.hhly.member.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
* 访问控制过滤器
* @author jiasx
* @create 2017/9/14 9:36
**/
public class PlatformAccessControlFilter extends PathMatchingFilter {

    private Logger logger = LoggerFactory.getLogger(PlatformAccessControlFilter.class);

    @Autowired
    private IRedisRepository iRedisRepository;

    private String[] unAuthUrls = null;

    public void setUnAuthUrl(String[] unAuthUrls) {
        this.unAuthUrls = unAuthUrls;
    }

    protected boolean isUnAuthUrl(ServletRequest request) {
        boolean flag = false;
        for(String unAuthUrl:unAuthUrls){
            if(this.pathsMatch(unAuthUrl, request)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     *
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     *
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
           throws Exception {
        logger.info("PlatformAccessControlFilter.onPreHandle()");
       if(isUnAuthUrl(request)){
           return true;
       }
        String userToken = CookieUtil.getCookieValue((HttpServletRequest) request, Constant.USER_TOKEN);
        if (userToken == null) {
            /**后台没有获取到用户令牌时，清空用户登录状态*/
            UserReqContextUtil.set(null);
            onLoginFail(response, CodeMsg.user_token_invalid);
            /**就直接返回给请求者.*/
            return false;
        }
        String redisKey = String.format(Constant.USER_TOKEN_REDIS_KEY, userToken);
        Object object = iRedisRepository.get(redisKey);

        logger.info("登录拦截器：userToken=" + userToken);

        if (object == null || !(object instanceof SysUser)) {
            onLoginFail(response,CodeMsg.user_token_invalid);
            /**就直接返回给请求者.*/
            return false;
        }else {
            /**刷新token有效期【redis,cookie】*/
            iRedisRepository.expire(redisKey, Constant.USER_TOKEN_EXPIRE, TimeUnit.SECONDS);
            CookieUtil.add((HttpServletResponse) response, Constant.USER_TOKEN, userToken, Constant.USER_TOKEN_EXPIRE);

            /**记录登录状态*/
            UserReqContextUtil.setRequestUser((SysUser)object);
            UserReqContextUtil.setToken(userToken);

            logger.info("登录拦截器：userId=" + UserReqContextUtil.getRequestUserId() + ",loginId=" + UserReqContextUtil.getRequestUser().getAccount());
            PlatformAuthenticationToken token = new PlatformAuthenticationToken((SysUser) object,userToken);
            try {
                SecurityUtils.getSubject().login(token);
            } catch (AuthenticationException e) {
                onLoginFail(response,CodeMsg.user_no_permission);
                /**就直接返回给请求者.*/
                return false;
            }
            return true;
        }
    }
 
    /**
     * 登录失败时默认返回401 状态码
     * */
    private void onLoginFail(ServletResponse response, CodeMsg code){
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ResponseT<String> resEntity = new ResponseT<>();
        resEntity.setCode(code.getCode());
        resEntity.setMsg(code.getMsg());
        resEntity.setData(null);
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JacksonUtil.toJson(resEntity));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw  new BizException(CodeMsg.system_error);
        }
    }

}