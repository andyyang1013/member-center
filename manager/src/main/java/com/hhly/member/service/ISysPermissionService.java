package com.hhly.member.service;

import com.baomidou.mybatisplus.service.IService;
import com.hhly.member.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 系统权限资源表 服务类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
public interface ISysPermissionService extends IService<SysPermission> {



    /**
     * 查询所有资源，树结构展示
     * @return
     */
    List<SysPermission> getTreeList();

    /**
     * 根据Id查询系统权限表
     * @param roleId
     * @return
     */
    List<SysPermission> getPermissionsByRoleId(Long roleId);


    /**
     * 根据用户Id查询系统权限表
     * @param userId
     * @return  List<SysPermission>
     */
    List<SysPermission> getPermissionsByUserId(Long userId);

    /**
     *
     * @param userId
     * @param token
     */
    void updateLoginUserPermission(Long userId,String token);

    /**
     * 校验所有id是有父id存在或自己就是父id
     * @param permissionIds
     * @return
     */
    boolean existParentId(String permissionIds);
}
