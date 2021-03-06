package com.hhly.member.service;

import com.baomidou.mybatisplus.service.IService;
import com.hhly.member.entity.SysUser;
import com.hhly.member.entity.SysUserRole;
import com.hhly.member.vo.SysUserVo;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
public interface ISysUserService extends IService<SysUser> {


    /**
     * 查询系统用户,带分页
     * @param entry
     * @return List<SysUser>
     */
    List<SysUser> findUserByRoleId(SysUserVo entry);

    /**
     * 新增用户和角色关系表
     * @param entry
     */
    void insertRoleRelation(SysUserRole entry);

    /**
     * 根据登录账号查询系统用户
     * @param account
     * @return SysUser
     */
    SysUser getByAccount(String account);

    /**
     * 删除系统用户和角色关系
     * @param userId
     */
    void deleteRoleRelationsByUserId(Long userId);

    /**
     * 删除角色小所有的用户
     * @param roleId
     */
    void deleteUsersByRoleId(Long roleId);




    /**
     * 插入用户和用户角色关系
     * @param sysUser
     * @param roleId
     */
    public void insert(SysUser sysUser, Long[] roleId);


    /**
     * 用户列表,排除加密信息
     * @return
     */
    List<SysUser> selectListExclude();


    /**
     * 修改用户及用户角色
     * @param dbSysUser
     * @param roleIds
     */
    void updateUser(SysUser dbSysUser, Long[] roleIds);

    /**
     * 删除用户
     * @param sysUser
     */
    void deleteByUser(SysUser sysUser);
}
