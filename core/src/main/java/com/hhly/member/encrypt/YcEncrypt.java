package com.hhly.member.encrypt;/**
 * Created by dell on 2018/2/27.
 */

import com.hhly.member.encrypt.util.YcEncryptUtil;
import com.hhly.member.exception.BizException;
import com.hhly.member.exception.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 益彩的加密算法
 *
 * @author jiasx
 * @create 2018-02-27 14:34
 **/
public class YcEncrypt implements IEncrypt {

    private Logger logger = LoggerFactory.getLogger(YcEncrypt.class);

    @Override
    public String getBackPassword(String password, String salt) {
        if(StringUtils.isEmpty(password)){
            return "";
        }
        try {
            return YcEncryptUtil.encrypt(password,salt);
        } catch (NoSuchAlgorithmException e) {
            logger.error("益彩密码后端加密失败",e);
            throw new BizException(CodeMsg.system_error);
        } catch (InvalidKeySpecException e) {
            logger.error("益彩密码后端加密失败",e);
            throw new BizException(CodeMsg.system_error);
        }
    }

    @Override
    public String getFrontPassword(String password, String salt) {
        if(StringUtils.isEmpty(password)){
            return "";
        }
        try {
            return YcEncryptUtil.getFrontEncryptedPassword(password);
        } catch (NoSuchAlgorithmException e) {
            logger.error("益彩密码前端加密失败",e);
            throw new BizException(CodeMsg.system_error);
        } catch (InvalidKeySpecException e) {
            logger.error("益彩密码前端加密失败",e);
            throw new BizException(CodeMsg.system_error);
        }
    }
}
