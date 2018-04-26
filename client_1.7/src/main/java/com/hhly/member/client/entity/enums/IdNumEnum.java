package com.hhly.member.client.entity.enums;/**
 * Created by dell on 2018/3/5.
 */

/**
 * 身份证验证状态
 *
 * @author jiasx
 * @create 2018-03-05 10:33
 **/
public enum IdNumEnum {
    /**
     * 未验证
     */
    NO_VERIFY(0, "未验证"),
    /**
     * 已验证
     */
    VERIFIED(1, "已验证");


    private int state; //状态
    private String remark;//描述

    IdNumEnum(int state, String remark) {
        this.state = state;
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public String getRemark() {
        return remark;
    }


    /**
     * 获取名称
     * @param state
     * @return
     */
    public String getNameByState(int state) {
        for (IdNumEnum curEnum : values()) {
            if (curEnum.getState() == state) {
                return curEnum.getRemark();
            }
        }
        return null;
    }
}
