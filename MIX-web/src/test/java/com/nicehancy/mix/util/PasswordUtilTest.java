package com.nicehancy.mix.util;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 密码工具测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/4/26 17:10
 **/
@Slf4j
public class PasswordUtilTest extends BaseSpringTest {

    @Test
    public void randomPasswordTest(){

        log.info("密码生成:{}", PasswordUtil.randomPassword());
    }
}
