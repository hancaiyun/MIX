package com.nicehancy.MIX.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 文件ID工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 14:19
 **/
public class FileIdUtil {

    /**
     * 私有构造函数
     */
    private FileIdUtil(){
    }

    /**
     * 文件id创建类
     * 创建16位唯一ID
     * @return          文件唯一id
     */
    public static String createFileId(){
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
