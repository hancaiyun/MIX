package com.nicehancy.mix.core;

import org.springframework.stereotype.Component;

/**
 * 测试core
 * <p>
 *     仅支持mysql关系型数据库
 *     不支持MongoDB
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 9:14
 **/
@Component
public class TestCore {

    //@Transactional(rollbackFor = { Exception.class })
    public void testMethod(){

        //数据库操作一

        //数据库操作二
    }
}
