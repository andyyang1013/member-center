package com.hhly.member.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hhly.member.entity.User;
import com.hhly.member.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账户认证表 Mapper 接口
 * </p>
 *
 * @author jiasx
 * @since 2018-02-07
 */
public interface UserMapper extends BaseMapper<User> {
    void updateLastLoginTimeById(User user);

    List<UserVO> selectByAccountAndSubCode(User user);

    List<UserVO> selectByAccount(@Param("account") String account);
    
    List<UserVO> selectByAccountAndType(@Param("account")String account,@Param("type")int type);

	Long exsistUser (@Param("account")String account,@Param("type")int type);

    List<UserVO> selectByEmail(@Param("email") String email);

    List<UserVO> selectByPhone(@Param("phone") String phone);

    List<UserVO> selectByAccountOrPhone(@Param("account") String phone);

	UserVO  selectBySubCodeAndSubUserId(@Param("subUserId")String subUserId,@Param("subCode")String subCode);

    /**
     * 获取用户列表
     * @param user
     * @return
     */
    List<UserVO> selectUserList(UserVO user);

    /**
     * 条件查询总记录
     * @param userVO
     * @return
     */
    int selectTotal(UserVO userVO);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    UserVO selectUserInfo(Long id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据用户ID查询用户账号和其他信息
     *
     * @param id 用户ID
     * @return 用户账号和其他信息
     * @author yangzhen
     */
    UserVO selectByUserId(Long id);

	UserVO queryUserInfoById(Long id);
}