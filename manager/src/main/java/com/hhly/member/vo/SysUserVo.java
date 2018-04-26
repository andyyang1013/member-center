package com.hhly.member.vo;


import com.hhly.member.entity.SysUser;
import lombok.Data;

/**
* 请前端传递的参数
* @author jiasx
* @create 2017/10/20 15:54
**/
@Data
public class SysUserVo extends SysUser {
    /**
     *  角色id
     */
    private Long roleId;

}
