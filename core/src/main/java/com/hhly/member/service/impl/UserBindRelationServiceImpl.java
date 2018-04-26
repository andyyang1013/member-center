package com.hhly.member.service.impl;

import com.hhly.member.entity.UserBindRelation;
import com.hhly.member.mapper.UserBindRelationMapper;
import com.hhly.member.service.IUserBindRelationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账号绑定关系表 服务实现类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
@Service
public class UserBindRelationServiceImpl extends ServiceImpl<UserBindRelationMapper, UserBindRelation> implements IUserBindRelationService {

    @Autowired
    private UserBindRelationMapper userBindRelationMapper;

    @Override
    public UserBindRelation selectByUserId(Long curUserId) {
        return userBindRelationMapper.selectByUserId(curUserId);
    }
}
