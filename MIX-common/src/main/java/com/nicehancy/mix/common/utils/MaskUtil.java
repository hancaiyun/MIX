package com.nicehancy.mix.common.utils;


import com.nicehancy.mix.common.enums.SensitiveTypeEnum;

/**
 * <p>
 *     掩码工具类
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/19 15:13
 **/
public class MaskUtil {

    /**
     * 私有构造函数
     */
    private MaskUtil(){
    }

    /**
     * 掩码处理
     * @param type              数据类型
     * @param str               待脱敏字符串
     * @return                  掩码
     */
    public static String getMask(String type, String str){

        /*
          姓名脱敏
         */
        if(SensitiveTypeEnum.NAME.getCode().equals(type)){
            int length = str.length();
            StringBuffer sb = new StringBuffer();
            for(int i =0; i < length-1; i++){
                sb.append("*");
            }
            return str.substring(0,1) + sb;
        }

        /*
          手机号脱敏
         */
        if(SensitiveTypeEnum.MOBILE.getCode().equals(type)){
            return str.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
        }

        /*
          身份证脱敏
         */
        if(SensitiveTypeEnum.IDCARD.getCode().equals(type)){
            return str.replaceAll("(?<=\\w{4})\\w(?=\\w{4})", "*");
        }

        /*
           邮箱脱敏
         */
        if(SensitiveTypeEnum.EMAIL.getCode().equals(type)){
            return str.replaceAll("(\\w+)\\w{5}@(\\w+)", "$1***@$2");
        }
        /*
          暂不支持的脱敏类型，返回原数据
         */
       return str;
    }
}
