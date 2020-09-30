package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.SendSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 短信发送工具类测试
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/9 17:13
 **/
@Slf4j
public class SmsTest extends BaseSpringTest {

    /**
     * 发送短信验证码
     */
    @Test
    public void sendVerCodeTest(){
        try {
            SendSmsUtil.sendVercode("19921577717");
        } catch (Exception e) {
            log.info("失败信息：", e);
        }
    }
}
