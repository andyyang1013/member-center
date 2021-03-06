package com.hhly.member.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.hhly.member.entity.SysRole;
import com.hhly.member.entity.SysRolePermission;
import com.hhly.member.mapper.SysRoleMapper;
import com.hhly.member.service.ISysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;



    /**
     * 新增系统角色,批量插入角色和权限的关系表
     * 先简单实现，回头重构
     * @param sysRole
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysRole sysRole,String permissionIds){
        sysRoleMapper.insert(sysRole);
        //增加角色和权限资源的关系
        if(StringUtils.isNotEmpty(permissionIds)){
            List<SysRolePermission> sysRolePermissions = setSysRolePermissions(sysRole, permissionIds);
            if(!sysRolePermissions.isEmpty()){
                sysRoleMapper.insertPermRelations(sysRolePermissions);
            }
        }
    }

    /**
     * 修改系统角色
     * @param sysRole
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRole sysRole,String permissionIds) {
        sysRoleMapper.updateById(sysRole);
        //增加角色和权限资源的关系
        if(StringUtils.isNotEmpty(permissionIds)){
            //删除以前的role和权限关系
            sysRoleMapper.deletePermRelationsByRoleId(sysRole.getId());
            List<SysRolePermission> sysRolePermissions = setSysRolePermissions(sysRole, permissionIds);
            if(!sysRolePermissions.isEmpty()){
                sysRoleMapper.insertPermRelations(sysRolePermissions);
            }
        }
    }

    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        return sysRoleMapper.getRolesByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRole(SysRole sysRole) {
        sysRoleMapper.deletePermRelationsByRoleId(sysRole.getId());
        sysRoleMapper.deleteUsersByRoleId(sysRole.getId());
        sysRoleMapper.deleteById(sysRole.getId());
    }

    @Override
    public boolean existRoleName(String roleName) {
        SysRole role = sysRoleMapper.selectByRoleName(roleName);
        //判断是用户组是否存在
        return role != null;
    }

    private List<SysRolePermission>  setSysRolePermissions(SysRole sysRole,String permissionIds){
        List<SysRolePermission> sysRolePermissions = new ArrayList<>();
        String[] permArr = permissionIds.split(",");
        for(String permStr:permArr){
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setId(IdWorker.getId());
            sysRolePermission.setRoleId(sysRole.getId());
            sysRolePermission.setPermissionId(Long.parseLong(permStr));
            sysRolePermission.setCreateUid(sysRole.getCreateUid());
            sysRolePermission.setCreateTime(sysRole.getCreateTime());
            sysRolePermission.setUpdateUid(sysRole.getId());
            sysRolePermission.setUpdateTime(sysRole.getUpdateTime());
            sysRolePermissions.add(sysRolePermission);
        }
        return sysRolePermissions;
    }

}
