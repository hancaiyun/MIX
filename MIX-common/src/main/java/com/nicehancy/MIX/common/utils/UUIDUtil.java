package com.nicehancy.MIX.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *     UUID工具类
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/6/3 11:31
 **/
public final class UUIDUtil {

    /**
     * 私有构造函数
     */
    private UUIDUtil(){
    }

    /**
     * UUID创建16位唯一编号
     * @return
     */
    public static String createNoByUUId() {

        int first = new Random(10).nextInt(8) + 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }
}