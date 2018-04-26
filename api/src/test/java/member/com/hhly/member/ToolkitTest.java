package member.com.hhly.member;/**
 * Created by dell on 2018/2/27.
 */

import com.hhly.member.encrypt.EncryptFactory;
import com.hhly.member.util.Toolkit;
import org.junit.Test;

/**
 * 工具类测试
 *
 * @author jiasx
 * @create 2018-02-27 14:53
 **/
public class ToolkitTest {

    @Test
    public void sha1Test(){
        System.out.println(Toolkit.getSha1("jsx12122212admin"));

    }

    @Test
    public void htPasswordProduce(){
        String password = "123456a";
        String salt = "9zm8OnPbCZ38mKGO9DOxcw==";
        String frontPass = EncryptFactory.builder().produceFrontPassword("ht",password,salt);
        String backPass = EncryptFactory.builder().produceBackPassword("ht",frontPass,salt);
        System.out.println(backPass);

    }

    @Test
    public void getFrontPassword(){
        String password = "a123456";
        String frontPass1 = EncryptFactory.builder().produceFrontPassword("ht",password,"");
        String frontPass2 = EncryptFactory.builder().produceFrontPassword("yc",password,"");
        System.out.println(frontPass1+";"+frontPass2);
    }
}
