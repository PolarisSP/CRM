import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * @author 沈鹏
 * @date 2020/5/20 -16:11
 */
public class ShiroTest {

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("admin");;
        int hashIterations = 100;

        Object result = new Md5Hash(credentials, salt, hashIterations);
        System.out.println(result);


        System.out.println("--------------------------");

        System.out.println("master分支更新");

        System.out.println("测试本地clone分支");

        System.out.println("Merge测试");

//        SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
//        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
//        return info;
    }
}
