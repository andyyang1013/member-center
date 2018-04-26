package com.hhly.member.entity.enums;/**
 * Created by dell on 2018/3/5.
 */

/**
 * 账户类型
 *
 * @author jiasx
 * @create 2018-03-05 10:33
 **/
public enum AccountTypeEnum {
    /**
     * 手机号
     */
    PHONE(1, "手机"),
    /**
     * 邮箱
     */
    EMAIL(2, "邮箱"),
    /**
     * 用户名
     */
    ACCOUNT(3, "用户名");


    private int code;
    private String name;

    AccountTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取名称
     * @param code
     * @return
     */
    public String getNameByCode(int code) {
        for (AccountTypeEnum sexEnum : values()) {
            if (sexEnum.getCode() == code) {
                return sexEnum.getName();
            }
        }
        return null;
    }
}
