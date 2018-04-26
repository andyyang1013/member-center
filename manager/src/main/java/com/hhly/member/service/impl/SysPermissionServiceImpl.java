package com.hhly.member.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hhly.member.Constant;
import com.hhly.member.config.filter.UserReqContextUtil;
import com.hhly.member.entity.SysPermission;
import com.hhly.member.entity.SysRole;
import com.hhly.member.entity.SysUser;
import com.hhly.member.mapper.SysPermissionMapper;
import com.hhly.member.mapper.SysRoleMapper;
import com.hhly.member.repository.IRedisRepository;
import com.hhly.member.service.ISysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 系统权限资源表 服务实现类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private IRedisRepository iRedisRepository;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysPermission> getPermissionsByRoleId(Long roleId) {
        return sysPermissionMapper.getPermissionsByRoleId(roleId);
    }

    /**
     *  查询所有资源，树结构展示
     **/
    @Override
    public List<SysPermission> getTreeList() {
        List<SysPermission> returnPermissions = new ArrayList<>();
        SysPermission condSysPermission = new SysPermission();
        condSysPermission.setDisabled(0);
        List<SysPermission> permissions = sysPermissionMapper.getList(condSysPermission);
        getTreeList(returnPermissions, permissions);
        return returnPermissions;
    }


    /**
     * 根据用户Id查询系统权限表
     * @param userId
     * @return  List<SysPermission>
     */
    @Override
    public List<SysPermission> getPermissionsByUserId(Long userId){
        return sysPermissionMapper.getPermissionsByUserId(userId);
    }

    @Override
    public void updateLoginUserPermission(Long userId,String token) {
        String redisKey = String.format(Constant.USER_TOKEN_REDIS_KEY, token);
        Object object = iRedisRepository.get(redisKey);
        List<SysRole> roles = sysRoleMapper.getRolesByUserId(userId);
        for(SysRole role:roles){
            role.setPermissions(sysPermissionMapper.getPermissionsByRoleId(role.getId()));
        }
        SysUser user = (SysUser) object;
        user.setRoles(roles);
        /**记录登录状态*/
        iRedisRepository.set(redisKey, user, Constant.USER_TOKEN_EXPIRE, TimeUnit.SECONDS);
        UserReqContextUtil.setRequestUser((SysUser)object);
        UserReqContextUtil.setToken(token);
//            PlatformAuthenticationToken userToken = new PlatformAuthenticationToken((SysUser) object,token);
//            try {
//                SecurityUtils.getSubject().login(userToken);
//            } catch (AuthenticationException e) {
//                /**就直接返回给请求者.*/
//            }
    }

    @Override
    public boolean existParentId(String permissionIds) {
        if(StringUtils.isNotEmpty(permissionIds)){
            String[] ids = permissionIds.split(",");
            List<String> list = Arrays.asList(ids);
            for (String id : ids) {
                //判断是否存在传入id是子id且没有父id传入
                SysPermission sysPermission = sysPermissionMapper.selectById(Long.valueOf(id));
                if (sysPermission.getParentId() != -1 && !list.contains(sysPermission.getParentId().toString())){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 获取树列表
     **/
    private void getTreeList(List<SysPermission> returnPermissions, List<SysPermission> permissions) {
        int rootParentId = -1;
        for (SysPermission permission : permissions) {
            if (permission.getParentId() == rootParentId) {
                returnPermissions.add(permission);
            }
        }

        for (SysPermission returnPermission : returnPermissions) {
            setChild(returnPermission, permissions);
        }
    }


    /**
     * 设置子元素
     **/
    private void setChild(SysPermission curPermission, List<SysPermission> permissions) {
        if (curPermission.getChildCount() > 0) {
            List<SysPermission> childPermissions = new ArrayList<>();
            for (SysPermission permission : permissions) {
                if (Objects.equals(permission.getParentId(), curPermission.getId())) {
                    childPermissions.add(permission);
                    setChild(permission, permissions);
                }
            }
            curPermission.setChilds(childPermissions);
        }
    }
}
