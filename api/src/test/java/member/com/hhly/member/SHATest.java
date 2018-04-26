package member.com.hhly.member;/**
 * Created by dell on 2018/2/27.
 */

import com.hhly.member.encrypt.util.HtEncryptUtil;
import com.hhly.member.encrypt.util.SHAEncryptUtil;
import com.hhly.member.util.Md5Util;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 工具类测试
 *
 * @author jiasx
 * @create 2018-02-27 14:53
 **/
public class SHATest {

    @Test
    public void sha1Test(){
        System.out.println(SHAEncryptUtil.sha1("jsx12122212admin"));

    }

    @Test
    public void sha256Test(){
        System.out.println(SHAEncryptUtil.sha256("jsx12122212admin"));

    }

    @Test
    public void sha512Test(){
        String salt = Hex.encodeHexString("seedp17882".getBytes(StandardCharsets.UTF_8));
        System.out.println(SHAEncryptUtil.sha512(Md5Util.md5Hex("123456a")+salt));
        System.out.println(HtEncryptUtil.sha512(HtEncryptUtil.md5("123456a").toLowerCase(),"seedp17882"));

    }
}
