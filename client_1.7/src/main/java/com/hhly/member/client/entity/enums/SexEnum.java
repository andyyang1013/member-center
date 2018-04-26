package com.hhly.member.client.entity.enums;/**
 * Created by dell on 2018/3/5.
 */

/**
 * 性别状态
 *
 * @author jiasx
 * @create 2018-03-05 10:33
 **/
public enum SexEnum {
    /**
     * 保密
     */
    SECRET(0, "保密"),
    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 女
     */
    FEMALE(2, "女");


    private int code; //性别
    private String name;//描述

    SexEnum(int code, String name) {
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
        for (SexEnum sexEnum : values()) {
            if (sexEnum.getCode() == code) {
                return sexEnum.getName();
            }
        }
        return null;
    }
}
