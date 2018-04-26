package com.hhly.member.entity.enums;/**
 * Created by dell on 2018/3/5.
 */

/**
 * 记录删除状态
 *
 * @author jiasx
 * @create 2018-03-05 10:33
 **/
public enum DelFlagEnum {
    /**
     * 未删除
     */
    NO_DELETE(0, "未删除"),

    /**
     * 已删除
     */
    DELETED(1, "已删除");


    private int state; //状态
    private String remark;//描述

    DelFlagEnum(int state, String remark) {
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
        for (DelFlagEnum curEnum : values()) {
            if (curEnum.getState() == state) {
                return curEnum.getRemark();
            }
        }
        return null;
    }
}
