package com.nicehancy.mix.manager.redis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

        String resultStr = (String) redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            if (connection.exists(redisKey)) {
                byte[] value = connection.get(redisKey);
                return redisTemplate.getStringSerializer().deserialize(value);
            }
            return null;
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

    /**
     * 6、查询key值的剩余超时时间
     * @param keyEnum           查询关键字
     * @return                  剩余秒数
     */
    public Long queryExpireTime(final String keyEnum){

        log.debug("queryExpireTime request:key={}", keyEnum);

        Long surplusTime = redisTemplate.getExpire(keyEnum, TimeUnit.SECONDS);

        log.debug("deleteObject result：{}", surplusTime);

        return surplusTime;
    }

    /**
     * 加锁
     *
     * @param key     key值
     * @param id      id
     * @param timeout 超时时间，单位/秒 注：超时时间建议比线程实际处理时间长一点
     * @return 计数结果值
     */
    public void lock(final String key, String id, final long timeout) {

        log.info("获取锁请求:key={}, id={}, timeout={}", key, id, timeout);

        //获取锁
        long start = System.currentTimeMillis();
        //获取失败， 循环等待或者在超时时间前结束
        try {
            for (; ; ) {
                //获取锁
                boolean lock = redisTemplate.hasKey(key);
                if (!lock) {
                    redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                                byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
                                byte[] redisValue = redisTemplate.getStringSerializer().serialize(id);
                                connection.set(redisKey, redisValue);
                                if (timeout > 0) {
                                    redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
                                }
                                return true;
                    });
                    log.info("持有锁: key={}", key);
                    return;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout*1000) {
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解锁
     * @param key               key值
     */
    public void unlock(final String key){

        log.info("解锁请求:key={}", key);

        Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return connection.del(redisKey);
        });

        log.info("解锁结果：{}", result);
    }

}