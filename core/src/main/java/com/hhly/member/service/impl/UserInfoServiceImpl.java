package com.hhly.member.service.impl;

import com.hhly.member.entity.UserInfo;
import com.hhly.member.mapper.UserInfoMapper;
import com.hhly.member.service.IUserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户扩展信息表 服务实现类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
	
}
