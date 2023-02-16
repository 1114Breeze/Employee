package cn.tedu.aweb;

import java.text.SimpleDateFormat;

public class test {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now3 = df.format(System.currentTimeMillis());
        System.out.println("now3:" + now3);
    }
}
