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
     * 发送短信
     *
     * @param phone     手机号
     * @param message   短信内容
     */
    public static void sendVercode(String phone, String message) throws Exception {

        //创建短信平台连接
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        //设置内容信息
        Map<String, String> map = new HashMap<>(10);
        map.put("message", message);
        map.put("number", phone);
        //发送短信
        String result = client.send(map);
        log.info("手机短息发送成功，手机号：{}, 短信发送内容：{}， 短信平台响应结果：{}", phone, message, result);
    }
}
