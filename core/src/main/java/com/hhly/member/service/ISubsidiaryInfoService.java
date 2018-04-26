package com.hhly.member.service;

import com.hhly.member.entity.SubsidiaryInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 子公司信息管理 服务类
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
public interface ISubsidiaryInfoService extends IService<SubsidiaryInfo> {

    /**
     * 根据apiKey获取子公司的配置信息
     * @param apiKey
     * @return   
     */  
    SubsidiaryInfo selectByApiKey(String apiKey);

}
