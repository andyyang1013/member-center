package com.hhly.member.entity.enums;

/**
 * Created by dell on 2018/3/5.
 */

/**
* 子公司接入状态
* @author jiasx
* @create 2018/3/5 17:19
**/
public enum SubsidiaryStateEnum {

    /**
     * 未接入
     */
    NOT_ACCESS(0, "未接入"),
    /**
     * 已接入
     */
    ACCESSED(1, "已接入");


    /**
     *  接入状态
     */
    private int state;

    /**
     *  名称
     */
    private String name;

    SubsidiaryStateEnum(int state, String name) {
        this.state = state;
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取名称
     * @param state
     * @return
     */
    public String getNameByState(int state) {
        for (SubsidiaryStateEnum curEnum : values()) {
            if (curEnum.getState() == state) {
                return curEnum.getName();
            }
        }
        return null;
    }
}
