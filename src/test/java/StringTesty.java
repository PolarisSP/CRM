/**
 * @author 沈鹏
 * @date 2021/4/8 -11:26
 */
public class StringTesty {
    public static void main(String[] args) {
        String s = "hello";
        String s1 = "he";
        String s2 = "llo";
        String s3 = s1 +s2;
        String s4 = "he" + "llo";
        String s5 = "hello";
        System.out.println(s == s3);
        System.out.println(s == s4);
        System.out.println(s == s5);
        String c= new String("a");
        String a = "a";
        String b = "b";
        System.out.println(a == c);
    }
}
