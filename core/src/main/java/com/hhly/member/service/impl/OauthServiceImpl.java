package com.hhly.member.service.impl;

import com.hhly.member.entity.Oauth;
import com.hhly.member.mapper.OauthMapper;
import com.hhly.member.service.IOauthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * OAuth2.0认证表，包括微信、qq，新浪微博等 服务实现类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
@Service
public class OauthServiceImpl extends ServiceImpl<OauthMapper, Oauth> implements IOauthService {
	
}
