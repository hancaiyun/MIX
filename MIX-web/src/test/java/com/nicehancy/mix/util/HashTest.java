package com.nicehancy.mix.util;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.map.HashMap;
import com.nicehancy.mix.common.utils.map.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * hash测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/20 13:51
 **/
@Slf4j
public class HashTest extends BaseSpringTest {

    @Test
    public void test(){

        Map<String, String> map = new HashMap<>();
        map.put("19921577717", "韩旭");
        map.put("15021500935", "小白");

        log.info("{}", map.hashCode());
    }
}
