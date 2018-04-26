package com.hhly.member.service;

import com.hhly.member.entity.UserBindRelation;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户账号绑定关系表 服务类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
public interface IUserBindRelationService extends IService<UserBindRelation> {

    /**
     * 查询当前用户ID的绑定关系
     *
     * @param curUserId 当前用户ID
     * @return 当前用户ID的绑定关系
     */
    UserBindRelation selectByUserId(Long curUserId);
}
