package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/7 14:41
 **/
@Slf4j
public class ListTest extends BaseSpringTest {

    /**
     *  数组列表去重测试
     */
    @Test
    public void quchongTest(){
        List<Integer> list = new ArrayList<>();
        list.add(26);
        list.add(39);
        list.add(5);
        list.add(40);
        list.add(39);
        list.add(25);

        List newList = list.stream().distinct().collect(Collectors.toList());
        log.info("原数组：{}", list);
        log.info("去重之后：{}", newList);
    }
}
