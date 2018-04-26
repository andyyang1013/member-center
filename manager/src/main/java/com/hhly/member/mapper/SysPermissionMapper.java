package com.hhly.member.mapper;

import com.hhly.member.entity.SysPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 系统权限资源表 Mapper 接口
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {


    /**
     * 查询系统权限表,带分页
     * @param entry
     * @return List<SysPermission>
     */
    List<SysPermission> getList(SysPermission entry);

    /**
     * 根据角色Id查询系统权限表
     * @param roleId
     * @return  List<SysPermission>
     */
    List<SysPermission> getPermissionsByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据用户Id查询系统权限表
     * @param userId
     * @return  List<SysPermission>
     */
    List<SysPermission> getPermissionsByUserId(@Param("userId") Long userId);

}