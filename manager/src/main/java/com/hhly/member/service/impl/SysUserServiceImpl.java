package com.hhly.member.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.hhly.member.entity.SysUser;
import com.hhly.member.entity.SysUserRole;
import com.hhly.member.mapper.SysUserMapper;
import com.hhly.member.service.ISysUserService;
import com.hhly.member.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findUserByRoleId(SysUserVo entry) {
        return sysUserMapper.findUserByRoleId(entry);
    }

    @Override
    public void insertRoleRelation(SysUserRole entry) {
        sysUserMapper.insertRoleRelation(entry);
    }

    @Override
    public SysUser getByAccount(String account) {
        return sysUserMapper.getByAccount(account);
    }

    @Override
    public void deleteRoleRelationsByUserId(Long userId) {
        sysUserMapper.deleteRoleRelationsByUserId(userId);
    }

    @Override
    public void deleteUsersByRoleId(Long roleId) {
        sysUserMapper.deleteUsersByRoleId(roleId);
    }

    /**
     * 插入用户和用户角色关系
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysUser sysUser,Long[] roleId){
        sysUserMapper.insert(sysUser);
        /**增加用户和角色的关系*/
        insertUserRole(sysUser,roleId);
    }

    @Override
    public List<SysUser> selectListExclude() {
        return sysUserMapper.selectListExclude();
    }

    @Override
    public void updateUser(SysUser dbSysUser, Long[] roleIds) {
        sysUserMapper.updateById(dbSysUser);
        //先删除之前所有角色关系
        sysUserMapper.deleteRoleRelationsByUserId(dbSysUser.getId());
        //新增用户角色关系
        insertUserRole(dbSysUser,roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUser(SysUser sysUser) {
        sysUserMapper.deleteRoleRelationsByUserId(sysUser.getId());
        sysUserMapper.deleteById(sysUser.getId());
    }

    private void insertUserRole(SysUser user,Long[] roleIds){
        for (Long roleId : roleIds) {
            if (roleId != null && roleId.longValue() != 0){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setId(IdWorker.getId());
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(user.getId());
                sysUserRole.setCreateUid(user.getUpdateUid());
                sysUserRole.setCreateTime(user.getCreateTime());
                sysUserRole.setUpdateUid(user.getUpdateUid());
                sysUserRole.setUpdateTime(user.getUpdateTime());
                sysUserMapper.insertRoleRelation(sysUserRole);
            }

        }

    }
}
