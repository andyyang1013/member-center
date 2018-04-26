package com.hhly.member.mapper;

import com.hhly.member.entity.SubsidiaryInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 子公司信息管理 Mapper 接口
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
public interface SubsidiaryInfoMapper extends BaseMapper<SubsidiaryInfo> {

    SubsidiaryInfo selectByApiKey(@Param("apiKey") String apiKey);

    List<SubsidiaryInfo> selectAllAccess();

}