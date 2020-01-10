package com.nicehancy.MIX.manager.redis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     redis服务
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/4/19 15:20
 **/
@Slf4j
@Component
public class RedisManager {

    /**
     * redisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 1、判断redis中是否具有某个key
     *
     * @param key   要查询的key
     * @return      若redis不含有这个key，则返回false；否则返回true
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 2.查询redis数据库，数据类型key-value形式
     *
     * @param keyEnum 查询关键字
     * @return String   key对应的value值
     */
    public String queryObjectByKey(final String keyEnum) {

        log.debug("queryObjectByKey request：{}", keyEnum);

        String resultStr = (String) redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
                if (connection.exists(redisKey)) {
                    byte[] value = connection.get(redisKey);
                    return redisTemplate.getStringSerializer().deserialize(value);
                }
                return null;
            }
        });

        log.debug("queryObjectByKey result：{}", resultStr);
        return resultStr;
    }

    /**
     * 3.插入redis数据库，数据类型key-value形式
     *
     * @param  obj      保存对象，
     * @param  keyEnum  关键字转换为JSON格式存放
     * @return          true-插入成功，false-插入失败
     */
    public boolean insertObject(final Object obj, final String keyEnum) {

        return insertObject(obj, keyEnum, 0L);
    }

    /**
     * 4.插入redis数据库,设置有效期，数据类型key-value形式
     *
     * @param  obj                  保存对象，转换为JSON格式存放
     * @param  keyEnum              关键字
     * @param  timeout              有效期（秒）
     * @return                      true-插入成功，false-插入失败
     * @throws DataAccessException  redis操作异常
     */
    public boolean insertObject(final Object obj, final String keyEnum, final long timeout) {

        if (obj == null) {
            return false;
        }

        log.debug("insertObject request：key={}，obj={}", keyEnum, obj);

        final String value = JSONObject.toJSONString(obj);
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            byte[] redisValue = redisTemplate.getStringSerializer().serialize(value);
            connection.set(redisKey, redisValue);
            if (timeout > 0) {
                redisTemplate.expire(keyEnum, timeout, TimeUnit.SECONDS);
            }
            return true;
        });

        log.debug("insertObject result：{}", result);
        return result;
    }

    /**
     * 5.删除redis保存对象，数据类型key-value形式
     *
     * @param  keyEnum 查询关键字
     * @return          true-删除成功，false-删除失败
     */
    public boolean deleteObject(final String keyEnum) {

        log.debug("deleteObject request:key={}", keyEnum);

        Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            return connection.del(redisKey);
        });

        log.debug("deleteObject result：{}", result);
        return result > 0;
    }
}