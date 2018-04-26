package com.hhly.member.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hhly.member.entity.SubsidiaryInfo;

import java.util.List;
import java.util.Map;


/**
 * 2018/2/27 10:29
 *
 * @author zb
 */
public interface SubsidiaryMapper extends BaseMapper<SubsidiaryInfo> {
    /**
     * 校验公司名称及编码
     * @param subsidiaryInfo
     * @return
     */
    SubsidiaryInfo checkCodeAndName(SubsidiaryInfo subsidiaryInfo);

    /**
     *  校验公司名称编码排除自己
     * @param subsidiaryInfo
     * @return
     */
    SubsidiaryInfo checkCodeAndNameExcludeThis(SubsidiaryInfo subsidiaryInfo);

    /**
     *  查询公司编码及名称
     * @return
     */
    List<Map> selectSubsidiaryNo();

}
