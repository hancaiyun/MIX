package com.nicehancy.MIX.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.Assert;

import java.lang.reflect.Type;
import java.util.List;

/**
 * <p>
 *     Gson工具类
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/4/19 9:13
 **/
public class GsonUtil {

    /**
     * java对象转json字符串
     * @param obj               message对象
     * @return                  字符
     */
    public static  String toJson(Object obj){
        Assert.notNull(obj, "null 不能转换为json");
        Gson gson = new Gson();
        return  gson.toJson(obj);
    }

    /**
     * json字符串转成java对象
     * @param str           字符串
     * @param type           class对象
     * @param <T>            class对象
     * @return
     */
    public static <T> T fromJson(String str,Class<T> type){
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转换成list数组
     * @param str   字符串
     * @return  list数组
     */
    public static List<Object> toListFromJson(String str){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>(){}.getType();
        return gson.fromJson(str, type);
    }
}