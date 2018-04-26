package com.hhly.member.client.test;/**
 * Created by dell on 2018/2/26.
 */


import com.hhly.member.client.Constant;
import com.hhly.member.client.Member;
import com.hhly.member.client.entity.MemberUser;
import com.hhly.member.client.entity.enums.AccountTypeEnum;
import com.hhly.member.client.util.MD5Util;
import com.hhly.member.client.util.Okhttp;
import com.hhly.member.client.util.PBKDF2Util;
import com.hhly.member.client.util.ReqContextUtil;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员中心api测试类
 *
 * @author jiasx
 * @create 2018-02-26 17:11
 **/
public class ApiTest {

    private long tokenExpireTime = 2*60*1000;

    @Before
    public void setEnvInfo(){
        System.out.println("准备设置环境信息");
        //电竞
        Constant.setEnvInfo("http://umcapi.zcopain.com/","e1a2c53d16666ece99cd63f669350e23","2a370730be48489b9633c262ba5ea752");
        //Constant.setEnvInfo("http://192.168.74.162:9902/","e1a2c53d16666ece99cd63f669350e23","2a370730be48489b9633c262ba5ea752");
        //一比分
//        Constant.setEnvInfo("http://umcapi.zcopain.com/","e1a2c53d16666ece99cd63f669350e52","2a370730be48489b9633c362ba5ea752");
    }

    @Test
    public void login() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String account = "zybhuyd5a015d0b1c42ehqM";
        String password = "tysx_20170415";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MD5Util.md5Hex(password)).append(";").append(PBKDF2Util.getFrontEncryptedPassword(password));
        MemberUser result = Member.builder().login(account, stringBuffer.toString(),tokenExpireTime);
        System.out.println("登录后的token：" + ReqContextUtil.getToken());
        System.out.println("用户信息：" + result.toString());
    }


    @Test
    public void loginDiff() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String account = "jsx17";
        String email = "";
        String phone = "18823161532 ";
        String password = "123456";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MD5Util.md5Hex(password)).append(";").append(PBKDF2Util.getFrontEncryptedPassword(password));
        MemberUser result = Member.builder().login(account, AccountTypeEnum.PHONE.getCode(), stringBuffer.toString(),tokenExpireTime);
        System.out.println("登录后的token：" + ReqContextUtil.getToken());
        System.out.println("用户信息：" + result.toString());
    }

    @Test
    public void loginWithVerifyCode() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String account = "13702582351";
        MemberUser result = Member.builder().loginWithVerifyCode(account,tokenExpireTime);
        System.out.println("登录后的token：" + ReqContextUtil.getToken());
        System.out.println("用户信息：" + result.toString());
    }

    @Test
    public void checkLoginStatus() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String token = "P7QArzJOVEPZ1UhhTtzTGA==";
        String result = Member.builder().checkLoginStatus(token);
        System.out.println("登录状态：" + result);
    }

    @Test
    public void getCurLoginUser() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String token = "P7QArzJOVEPZ1UhhTtzTGA==";
        MemberUser result = Member.builder().getCurLoginUser(token);
        System.out.println("当前登录用户：" + result);
    }

    @Test
    public void modifyTokenExpireTime() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String token = "U3S1mopnarAfNrfs/0V6Vg==111";
        String result = Member.builder().modifyTokenExpireTime(token,30000);
        System.out.println("过期时间修改结果：" + result);
    }

    @Test
    public void logout() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String token = "WfHDzuM54ssC+XqV2iRa5A==";
        String result = Member.builder().loginOut(token);
        System.out.println("退出结果：" + result);
    }

    @Test
    public void register() throws InvalidKeySpecException, NoSuchAlgorithmException {
        MemberUser memberUser = new MemberUser();
        memberUser.setAccount("jsx20");
        memberUser.setPhone("13834567822");
        memberUser.setEmail("13831@45678914");
        memberUser.setPassword(MD5Util.md5Hex("123456"));
        memberUser.setSalt("test");
        memberUser.setNickName("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        memberUser.setSex(2);
        memberUser.setImage("http://www.hhly.com");
        memberUser.setName("测试用户");
        memberUser.setIdNumber("420625198603296918");
        MemberUser result = Member.builder().register(memberUser);
        System.out.println("注册用户详情：" + result.toString());

//        String result1 = Member.builder().registerConfirm(result.getId(), 1);
//        System.out.println("注册用户确认结果：" + result1);
    }

    @Test
    public void registerConfirm() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Long id = 978603256660193282L;
        String result = Member.builder().registerConfirm(id, 1);
        System.out.println("注册用户确认结果：" + result);
    }

    @Test
    public void query() {
        String account = "1457219340@qq.com";
        int accountType = 2;
        System.out.println(Member.builder().queryUserInfo(account, accountType));
    }

    @Test
    public void queryUserInfoById() {
        Long id = 972018545065979906L;
        System.out.println(Member.builder().queryUserInfoById(id));
    }

    @Test
    public void batchQueryUserInfo() {
        String accounts = "dydj200001,test";
        int accountType = 3;
        System.out.println(Member.builder().batchQueryUserInfo(accounts, accountType));
    }

    @Test
    public void exsist() {
        int accountType = 2;
        String account = "1457219340@qq.com";
        System.out.println(Member.builder().exsistUser(account, accountType));
    }


    @Test
    public void updateUserInfo() {
        String token = "HweJBTZuU41gGXYu21jh9g==";
        MemberUser user = new MemberUser();
        user.setId(974488123016609794L);
        user.setNickName("昵称");
        user.setPhone("123456789123456789_1122asadadadsadada");
        user.setSex(-1);
        Member.builder().updateUserInfo(token, user);
    }

    @Test
    public void bindAccounts() {
        String token = "token";
        Long curUserId = 123L;
        String bindUserIds = "456,789";
        Member.builder().bindAccounts(token, curUserId, bindUserIds);
    }

    @Test
    public void resetPassword() {
        Long id = 979198580124041217L;
        String newPassword = "9cbf8a4dcb8e30682b927f352d6559a0;";
        System.out.println(Member.builder().resetPassword(id, newPassword).toString());
    }

    @Test
    public void resetPasswordByEmail() {
        String newPassword = "9cbf8a4dcb8e30682b927f352d6559a0";
        System.out.println(Member.builder().resetPasswordByEmail("yangz928@13322ty.com", newPassword).toString());
    }

    @Test
    public void modifyPassword() {
        Long id = 976389790704304129L;
        String sourcePassword = "e10adc3949ba59abbe56e057f20f883e;97027ef9edf42848737f72f01f085ab0b28715f9f62486d763d4a3487bf0eb45c3402e3ef7b2a02c61ef2e3fe59657030923306dce8983061456323c8a441389";
        String modifiedPassword = "dc483e80a7a0bd9ef71d8cf973673924;c6af71f1f0a407d73217808f9bed310ca929a5d87a7a3711d648c0df3f1b9ee70d7007448a869e42046c5435bee439a338b05583c44d1c7c1264758d4dabc573";
        System.out.println(Member.builder().modifyPassword(id, sourcePassword, modifiedPassword).toString());
    }


    @Test
    public void insertUser(){
        String url = "http://10.41.12.85:9903/superform/user/insert";
        java.util.Map<String, String> headersParams = new HashMap<>();
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("account","111");
        bodyParams.put("email","2222");

        String resuot = Okhttp.builder().post(url,headersParams,bodyParams);
        System.out.println(resuot);

    }
}
