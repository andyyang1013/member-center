package com.hhly.member.client.entity;/**
 * Created by dell on 2018/2/26.
 */

import lombok.Data;

import java.util.Date;

/**
 * 用户基本信息
 *
 * @author jiasx
 * @create 2018-02-26 14:22
 **/
@Data
public class MemberUser {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 用户基础信息id
     */
    private Long userInfoId;
    /**
     * 用户名/账号
     */
    private String account;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密盐，只对新账号有效
     */
    private String salt;
    /**
     * 子公司标识
     */
    private String subsidiaryCode;
    /**
     * 子公司用户id，新注册的和主键保持一致
     */
    private String subsidiaryUserId;
    /**
     * 注册时间
     */
    private Date regTime;
    /**
     * 最近登录时间
     */
    private Date lastLoginTime;
    /**
     * 删除状态，0 未删除 1 已删除 默认 0
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建用户Id
     */
    private Long createUid;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新用户id
     */
    private Long updateUid;

    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 性别,0 保密1 男 2 女 默认为0
     */
    private Integer sex = 0;
    /**
     * 头像url地址
     */
    private String image;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 身份证号验证状态,0 未验证 1已验证 默认 0
     */
    private Integer idNumberState = 0;

}
