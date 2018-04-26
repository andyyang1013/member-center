package com.hhly.member.config.filter.shiro;


import com.hhly.member.entity.SysUser;
import org.apache.shiro.authc.AuthenticationToken;

/**
*
* 用于授权的Token对象
 * 其中user是通过token读取的存在redis中的对象,userToken是为了给shiro做验证使用的，暂无用处
* @author jiasx
* @create 2017/9/13 16:59
**/
public class PlatformAuthenticationToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;
    private SysUser user;
    private String userToken;

    public PlatformAuthenticationToken(SysUser user, String userToken) {
        this.user = user;
        this.userToken = userToken;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public Object getCredentials() {
        return this.userToken;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}