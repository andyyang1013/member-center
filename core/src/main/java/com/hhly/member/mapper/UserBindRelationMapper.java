package com.hhly.member.mapper;

import com.hhly.member.entity.UserBindRelation;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 用户账号绑定关系表 Mapper 接口
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
public interface UserBindRelationMapper extends BaseMapper<UserBindRelation> {

    /**
     * 查询当前用户ID的绑定关系
     *
     * @param curUserId 当前用户ID
     * @return 当前用户ID的绑定关系
     */
    UserBindRelation selectByUserId(Long curUserId);
}