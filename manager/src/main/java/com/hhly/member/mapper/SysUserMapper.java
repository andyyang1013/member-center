package com.hhly.member.mapper;

import com.hhly.member.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hhly.member.entity.SysUserRole;
import com.hhly.member.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 系统用户表 Mapper 接口
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

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
    SysUser getByAccount(@Param("account") String account);

    /**
     * 删除系统用户和角色关系
     * @param userId
     */
    void deleteRoleRelationsByUserId(Long userId);

    /**
     * 删除角色小所有的用户
     * @param roleId
     */
    void deleteUsersByRoleId(@Param("roleId") Long roleId);

    /**
     * 用户列表,排除加密信息
     * @return
     */
    List<SysUser> selectListExclude();

    /**
     * 查询用户角色所有关系列表
     * @param dbSysUser
     * @return
     */
    List<SysUserRole> selectUserRoles(SysUser dbSysUser);
}