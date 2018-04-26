package com.hhly.member.service.impl;

import com.hhly.member.CommonConstant;
import com.hhly.member.entity.SubsidiaryInfo;
import com.hhly.member.mapper.SubsidiaryInfoMapper;
import com.hhly.member.service.ISubsidiaryInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 子公司信息管理 服务实现类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
@Service
public class SubsidiaryInfoServiceImpl extends ServiceImpl<SubsidiaryInfoMapper, SubsidiaryInfo> implements ISubsidiaryInfoService {

    @Autowired
    private SubsidiaryInfoMapper subsidiaryInfoMapper;

    @Cacheable(value= CommonConstant.CACHE_NAME_FOLDER, key= "#apiKey")
    @Override
    public SubsidiaryInfo selectByApiKey(String apiKey) {
        return subsidiaryInfoMapper.selectByApiKey(apiKey);
    }
}
