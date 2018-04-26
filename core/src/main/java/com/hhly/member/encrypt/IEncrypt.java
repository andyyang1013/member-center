package com.hhly.member.encrypt;/**
 * Created by dell on 2018/2/27.
 */

/**
 * 加密算法接口
 *
 * @author jiasx
 * @create 2018-02-27 14:32
 **/
public interface IEncrypt {

    /**
     * 后端加密算法
     * @param password 密码（经过前端加密后的密码）
     * @param salt 后端加密盐，从数据库中得出
     * @return
     */
    String getBackPassword(String password, String salt);

    /**
     * 前端加密算法
     * @param password 密码（明文密码）
     * @param salt 加密盐，只有部分公司使用到
     * @return
     */
    String getFrontPassword(String password, String salt);
}
