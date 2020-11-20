package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p>
 *     拷贝
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/16 11:13
 **/
@Slf4j
public class CopyTest extends BaseSpringTest {

    @Test
    public void copyTest() throws Exception {
        Person person1 = new Person();
        person1.setName("wang");
        person1.setSex("MAN");
        person1.setStudent(new Student());
        Person person2 = (Person)person1.clone();
        person1.setName("haha");

        String str1 = "abc";
        String str2 = "abc";
        //Person person3 = (Person)person1.deepClone();
        log.info("person1:{}", person1);
        log.info("person2:{}", person2);
        //log.info("person3:{}", person3.hashCode());

        log.info("str1:{},str2:{}", System.identityHashCode(str1), System.identityHashCode(str2));
    }

    public void test(){


    }
}
