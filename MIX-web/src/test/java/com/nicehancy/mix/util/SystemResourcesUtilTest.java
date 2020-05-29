package com.nicehancy.mix.util;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.SystemResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 系统资源获取测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/27 14:27
 **/
@Slf4j
public class SystemResourcesUtilTest extends BaseSpringTest {

    @Test
    public void test(){

        log.info("{}", SystemResourceUtil.getCpuRatioForWindows());
        log.info("{}", SystemResourceUtil.getMemery());
        log.info("{}", SystemResourceUtil.getTotalDisk());

    }
}
