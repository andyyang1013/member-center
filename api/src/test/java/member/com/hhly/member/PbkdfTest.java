package member.com.hhly.member;/**
 * Created by dell on 2018/3/1.
 */

import com.hhly.member.encrypt.util.YcEncryptUtil;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 益彩加密算法验证
 *
 * @author jiasx
 * @create 2018-03-01 16:38
 **/
public class PbkdfTest {

    @Test
    public void verifyFrontPass() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String password = "hhly_123456";
        String frontPass = YcEncryptUtil.getFrontEncryptedPassword(password);
        System.out.println(frontPass);
    }

}
