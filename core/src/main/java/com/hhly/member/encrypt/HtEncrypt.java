package com.hhly.member.encrypt;/**
 * Created by dell on 2018/2/27.
 */

import com.hhly.member.encrypt.util.HtEncryptUtil;
import com.hhly.member.util.Md5Util;
import org.springframework.util.StringUtils;

/**
 * 华体的加密算法
 *
 * @author jiasx
 * @create 2018-02-27 14:34
 **/
public class HtEncrypt implements IEncrypt {
    @Override
    public String getBackPassword(String password, String salt) {
        if(StringUtils.isEmpty(password)){
            return "";
        }
        return HtEncryptUtil.sha512(password.toLowerCase(),salt);
    }

    @Override
    public String getFrontPassword(String password, String salt) {
        if(StringUtils.isEmpty(password)){
            return "";
        }
        return Md5Util.md5Hex(password).toLowerCase();
    }
}
