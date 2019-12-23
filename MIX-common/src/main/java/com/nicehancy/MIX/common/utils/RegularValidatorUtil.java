package com.nicehancy.MIX.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *     正则校验工具类
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/4/18 14:00
 **/
@Slf4j
public class RegularValidatorUtil {

    /**
     * 私有构造函数
     */
    private RegularValidatorUtil(){
    }

    /**
     * 由数字组成
     * @param str           待校验字符串
     * @return              验证结果
     */
    public static boolean isNum(String str){

        String reg = "^[0-9]*$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

    /**
     * 由字母组成
     * @param str           待校验字符串
     * @return              验证结果
     */
    public static boolean isLetter(String str){

        String reg = "^[A-Za-z]+$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

    /**
     * 由数字字母组成
     * @param str         待校验字符串
     * @return            校验结果true-是，false-否
     */
    public static boolean isNumAndLetter(String str){

        String reg = "[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

    /**
     * 邮箱验证
     * @param str           待验证字符串
     * @return              验证结果
     */
    public static boolean isEmail(String str){

        String reg = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }
}