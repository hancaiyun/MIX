package com.nicehancy.mix.util;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.SensitiveWordFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Set;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/5 14:05
 **/
@Slf4j
public class SensitiveUtilTest extends BaseSpringTest {

    @Test
    public void checkTest(){

        String string = "福音会";
        boolean isContain = SensitiveWordFilterUtil.isContainSensitiveWord(string, 1);
        log.info("isContain：{}", isContain);
    }

    @Test
    public void getSensitiveWordTest(){

        String str = "太多的伤感情怀也许只局限于饲养基地南怀那些";
        Set<String> strings =  SensitiveWordFilterUtil.getSensitiveWord(str, 1);
        log.info("{}", strings);
    }
}
