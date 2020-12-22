package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/12/8 19:10
 **/
@Slf4j
public class MyTest extends BaseSpringTest {

    @Test
    public void getSubStringLength(){

        String str = "abcabc";
        char[] charList = str.toCharArray();
        Set<String> set = new HashSet();
        for(char a:charList){
            set.add(String.valueOf(a));
        }
        log.info("{}", set.size());
    }
}
