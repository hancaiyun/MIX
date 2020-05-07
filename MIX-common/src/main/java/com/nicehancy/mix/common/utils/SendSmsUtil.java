package com.nicehancy.mix.common.utils;

import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 短信发送工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/9 16:51
 **/
@Slf4j
public class SendSmsUtil {

    //URL
    private static final String apiUrl = "https://sms_developer.zhenzikj.com";

    //appId
    private static final String appId = "104227";

    //appSecret
    private static final String appSecret = "a6a9abbe-f3a8-4222-b92c-eae9ca3e6da9";

    /**
     * 无参构造函数
     */
    private SendSmsUtil() {
    }

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     */
    public static String sendVercode(String phone) throws Exception {

        //生成6位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "欢迎注册MIX账号，您的验证码为:" + verifyCode + "，该码5分钟内有效";

        //发送短信
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("number", phone);
        String result = client.send(map);
        log.info("手机短信验证码发送内容：{}， 发送结果：{}", message, result);
        //返回验证码
        return verifyCode;
    }
}
