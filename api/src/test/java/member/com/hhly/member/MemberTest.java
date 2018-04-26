package member.com.hhly.member;

import com.hhly.member.MemberApiApplication;
import com.hhly.member.encrypt.EncryptFactory;
import com.hhly.member.encrypt.util.YcEncryptUtil;
import com.hhly.member.service.IUserService;
import com.hhly.member.util.Md5Util;
import com.hhly.member.util.SignUtil;
import com.hhly.member.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
* 测试
* @author jiasx
* @create 2018/3/19 11:29
**/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemberApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MemberTest {

    private int initNum = 100000;
    private long mchId = 187636528074330112L;

    @Autowired
    private IUserService userService;


    @Test
    public void testInsertUser() throws InvalidKeySpecException, NoSuchAlgorithmException {
        int size = 100000;
        UserVO userVO = null;
        File outFile = new File("D:/会员中心性能测试用户.txt");
        long startTime  =  System.currentTimeMillis();
//        for (int i = 0; i < size; i++) {
//            userVO = new UserVO();
//            userVO.setAccount("ht_performa" + i);
//            userVO.setPassword(Md5Util.md5Hex("hhly_123456"));
//            userVO.setNickName("华体性能测试用户" + i);
//            userVO.setSex(2);
//            userVO.setImage("http://www.hhly.com");
//            userVO.setName("华体性能测试用户");
//            userVO.setSubsidiaryCode("ht");
//            userVO.setEncryptType("ht");
//            userService.register(userVO);
//            writeAppendText(outFile,userVO.getAccount()+",hhly_123456");
//        }
        for (int i = 0; i < size; i++) {
            userVO = new UserVO();
            userVO.setAccount("yc_performa" + i);
            userVO.setPassword(YcEncryptUtil.getFrontEncryptedPassword("hhly_123456"));
            userVO.setNickName("益彩性能测试用户" + i);
            userVO.setSex(2);
            userVO.setImage("http://www.hhly.com");
            userVO.setName("益彩性能测试用户");
            userVO.setSubsidiaryCode("yc");
            userVO.setEncryptType("yc");
            userService.register(userVO);
            writeAppendText(outFile,userVO.getAccount()+",hhly_123456");
        }

        for (int i = 0; i < size; i++) {
            userVO = new UserVO();
            userVO.setAccount("dj_performa" + i);
            userVO.setPassword(EncryptFactory.builder().produceFrontPassword("dj","hhly_123456",""));
            userVO.setNickName("电竞性能测试用户" + i);
            userVO.setSex(2);
            userVO.setImage("http://www.hhly.com");
            userVO.setName("电竞性能测试用户");
            userVO.setSubsidiaryCode("dj");
            userVO.setEncryptType("dj");
            userService.register(userVO);
            writeAppendText(outFile,userVO.getAccount()+",hhly_123456");
        }
        long endTime  =  System.currentTimeMillis();
        System.out.println("生成用户时间："+(endTime-startTime)/1000);
    }




    @Test
    public void testParam() throws InvalidKeySpecException, NoSuchAlgorithmException {
        int size = 100000;
        File outFileHt = new File("D:/会员中心性能测试用户-ht.txt");
        File outFileYC = new File("D:/会员中心性能测试用户-yc.txt");
        File outFileDj = new File("D:/会员中心性能测试用户-dj.txt");
        String password = "hhly_123456";
        Map<String,String> paramMap = new HashMap<>();
        long startTime  =  System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            paramMap.put("account","ht_performa" + i);
            paramMap.put("password",getFrontPassword(password));
            paramMap.put("apiKey","e1a2c53d16666ece99cd63f669350e5e");
            String sign = SignUtil.sign(paramMap,"2a370730be48489b9633c362ba5ea756");
            writeAppendText(outFileHt,paramMap.get("account")+","+paramMap.get("password")+","+paramMap.get("apiKey")+","+sign);
        }
        for (int i = 0; i < size; i++) {
            paramMap.put("account","yc_performa" + i);
            paramMap.put("password",getFrontPassword(password));
            paramMap.put("apiKey","e1a2c53d166daece99cd63f669350e23");
            String sign = SignUtil.sign(paramMap,"2a370730be48bc9b9633c262ba5ea752");
            writeAppendText(outFileYC,paramMap.get("account")+","+paramMap.get("password")+","+paramMap.get("apiKey")+","+sign);
        }

        for (int i = 0; i < size; i++) {
            paramMap.put("account","dj_performa" + i);
            paramMap.put("password",getFrontPassword(password));
            paramMap.put("apiKey","e1a2c53d16666ece99cd63f669350e23");
            String sign = SignUtil.sign(paramMap,"2a370730be48489b9633c262ba5ea752");
            writeAppendText(outFileDj,paramMap.get("account")+","+paramMap.get("password")+","+paramMap.get("apiKey")+","+sign);
        }
        long endTime  =  System.currentTimeMillis();
        System.out.println("生成用户时间："+(endTime-startTime)/1000);
    }

    private  String getFrontPassword(String password){
        String frontPass1 = EncryptFactory.builder().produceFrontPassword("ht",password,"");
        String frontPass2 = EncryptFactory.builder().produceFrontPassword("yc",password,"");
        return frontPass1+";"+frontPass2;
    }


    private void writeAppendText(File file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(content + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}