package com.hhly.member.service;

import com.hhly.member.entity.UserTemp;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息临时表，为了接收历史数据 服务类
 * </p>
 *
 * @author jiasx
 * @since 2018-03-01
 */
public interface IUserTempService extends IService<UserTemp> {

    void importHistoryData();

}
