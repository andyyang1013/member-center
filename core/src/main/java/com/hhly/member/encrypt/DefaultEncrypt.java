package com.hhly.member.encrypt;/**
 * Created by dell on 2018/2/27.
 */

/**
 * 默认的加密算法
 *
 * @author jiasx
 * @create 2018-02-27 14:34
 **/
public class DefaultEncrypt implements IEncrypt {
    @Override
    public String getBackPassword(String password, String salt) {
        return null;
    }

    @Override
    public String getFrontPassword(String password, String salt) {
        return null;
    }
}
