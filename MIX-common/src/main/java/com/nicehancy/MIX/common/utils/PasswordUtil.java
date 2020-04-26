package com.nicehancy.MIX.common.utils;

import java.util.Random;

/**
 * 密码工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/21 12:23
 **/
public class PasswordUtil {

    /**
     * 私有构造函数
     */
    private PasswordUtil(){
    }

    /**
     * 获取随机密码, 定长8位
     * @return          密码（明文）
     */
    public static String randomPassword(){

        StringBuilder password = new StringBuilder();

        //初始化数字英文和符号
        String num = "0123456789";
        String english = "qwertyuiopasdfghjklzxcvbnm";
        String englishBig = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String symbol = "!@#$%^&*";
        String stringSum = num + english + englishBig + symbol;
        int length = stringSum.length();
        //定义密码长度,固定长度8
        int passwordLength = 8;
        for (int i = 0; i < passwordLength; i++) {
            Random random = new Random();
            int a = random.nextInt(length + 1);
            char one = stringSum.charAt(a);
            password.append(one);
        }

        return password.toString();
    }

}
