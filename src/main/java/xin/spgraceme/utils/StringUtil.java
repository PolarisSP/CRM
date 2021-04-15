package xin.spgraceme.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * @author 沈鹏
 * @date 2020/5/20 -16:29
 */
public class StringUtil {


    private static final String str = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ0123456789";


    /**
     * 随机生成指定长度的字符串
     * @param len
     * @return
     */
    public static String randomStr(int len){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < len;i++){
            sb.append(str.charAt((int)(Math.random()*str.length())));
        }
        return sb.toString();
    }



    /**
     * 对密码进行加密处理
     * @param password
     * @param salt
     * @param times
     * @return
     */
    public static String encodePassword(String password,String salt,int times){
        Md5Hash md5Hash = new Md5Hash(password,salt,times);
        return md5Hash.toString();
    }


    public static String encodePassword(String password,String salt){
        Md5Hash md5Hash = new Md5Hash(password,salt,100);
        return md5Hash.toString();
    }


    /**
     * 图形验证码的字符内容
     * @return
     */
    public static String randomStr(){
        return randomStr(4);
    }

    /**
     * 生成随机id
     * @return
     */
    public static  String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


}
