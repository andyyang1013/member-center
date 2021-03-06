package com.hhly.member.web;/**
 * Created by dell on 2017/11/27.
 */

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hhly.member.Constant;
import com.hhly.member.entity.SysPermission;
import com.hhly.member.entity.SysRole;
import com.hhly.member.entity.SysUser;
import com.hhly.member.exception.BizException;
import com.hhly.member.exception.CodeMsg;
import com.hhly.member.repository.IRedisRepository;
import com.hhly.member.service.ISysPermissionService;
import com.hhly.member.service.ISysRoleService;
import com.hhly.member.service.ISysUserService;
import com.hhly.member.util.CookieUtil;
import com.hhly.member.util.Toolkit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 后台登录
 *
 * @author jiasx
 * @create 2017-11-27 11:19
 **/
@RestController
@RequestMapping("/admin")
public class LoginController extends BaseController{

    @Autowired
    private IRedisRepository redisRepository;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysPermissionService sysPermissionService;

    @RequestMapping("/login")
    public String login(String account,String password){
        logger.info("登录：account=" + account + ",password=" + password );

        /**请求参数空值判断*/
        if (StringUtils.isEmpty(account)||StringUtils.isEmpty(password)) {
            throw new BizException(CodeMsg.param_note_blank);
        }

        /**查询用户是否存在*/
        SysUser query = new SysUser();
        query.setAccount(account);
        SysUser loginUser =sysUserService.selectOne(new EntityWrapper<>(query));
        if (loginUser==null) {
            throw new BizException(CodeMsg.account_password_error);
        }

        /**
         * 判断用户是否被禁用
         */
        if (loginUser.getDisabled() == 1){
            throw new BizException(CodeMsg.user_has_disabled);
        }

        /**判断用户密码是否正确*/
        /**密码二次加密*/
        password = Toolkit.encrypt(password, loginUser.getSalt());
        if (!loginUser.getPassword().equalsIgnoreCase(password)) {
            throw new BizException(CodeMsg.account_password_error);
        }

        List<SysRole> roles = sysRoleService.getRolesByUserId(loginUser.getId());
        for(SysRole role:roles){
            role.setPermissions(sysPermissionService.getPermissionsByRoleId(role.getId()));
        }
        loginUser.setRoles(roles);
        /**记录最后登录时间*/
        Date lastLoginTime = new Date();
        loginUser.setUpdateUid(loginUser.getId());
        loginUser.setUpdateTime(lastLoginTime);
        loginUser.setLastLoginTime(lastLoginTime);
        sysUserService.updateById(loginUser);
        //生成user token
        String token = Toolkit.makeToken();
        redisRepository.set(String.format(Constant.USER_TOKEN_REDIS_KEY, token), loginUser, Constant.USER_TOKEN_EXPIRE, TimeUnit.SECONDS);
        CookieUtil.add(response, Constant.USER_TOKEN, token, Constant.USER_TOKEN_EXPIRE);
        logger.info("登录成功：userToken=" + token);
        //返回用户名
        return loginUser.getAccount();
    }


    /**
     * 前端全局验证用户登录状态（每个页面调用一次）
     * @param userToken
     * @return
     */
    @RequestMapping("/checkLoginStatus")
    public String checkLoginStatus(@CookieValue(name = Constant.USER_TOKEN, required = false) String userToken) {
        logger.info("全局验证用户登录状态:userToken=" + userToken);
        if (StringUtils.isEmpty(userToken)) {
            throw new BizException(CodeMsg.token_not_blank);
        }
        Object object = redisRepository.get(String.format(Constant.USER_TOKEN_REDIS_KEY, userToken));
        if (object == null || !(object instanceof SysUser)) {
            /**token失效，需要重新登录*/
            throw new BizException(CodeMsg.user_token_invalid);
        }
        return SUCCESS;
    }

    /**
     * 获取当前登录的用户详细信息
     * @param userToken
     * @return
     */
    @RequestMapping("/getCurUser")
    public SysUser getCurUser(@CookieValue(name = Constant.USER_TOKEN) String userToken) {
        Object object = redisRepository.get(String.format(Constant.USER_TOKEN_REDIS_KEY, userToken));
        if (object == null || !(object instanceof SysUser)) {
            /**token失效，需要重新登录*/
            throw new BizException(CodeMsg.user_token_invalid);
        }
        return (SysUser)object;
    }


    /**
     * 登出
     * 1.清除captchaToken,userToken [cookie]
     * 2.清除userToken    [redis]
     */
    @RequestMapping("/loginOut")
    public String loginOut(){
        String userToken = CookieUtil.getCookieValue(request, Constant.USER_TOKEN);
        if(StringUtils.isNotEmpty(userToken)){
            CookieUtil.remove(response, Constant.USER_TOKEN);
            redisRepository.del(String.format(Constant.USER_TOKEN_REDIS_KEY,userToken));
            removeContext();
        }
        return SUCCESS;
    }

    /**
     *通过角色ID查询菜单权限
     */
    @RequestMapping("/getMenuPermissionsMap")
    public Map<String,Boolean> getUserPermissionsMap() {
        List<SysPermission> sysPermissions = sysPermissionService.getPermissionsByUserId(getCurUserId());
        List<SysPermission> allSysPermissions = sysPermissionService.selectList(new EntityWrapper<>());
        if(sysPermissions== null || sysPermissions.isEmpty()){
            throw new BizException(CodeMsg.user_no_permission);
        }
        Map<String,Boolean> permissionMap = new HashMap<>(allSysPermissions.size());
        for(SysPermission sysPermission:allSysPermissions){
            if (sysPermissions.contains(sysPermission)){
                permissionMap.put(sysPermission.getResourceCode(),true);
            }else {
                permissionMap.put(sysPermission.getResourceCode(),false);
            }
        }
        return permissionMap;
    }

}
