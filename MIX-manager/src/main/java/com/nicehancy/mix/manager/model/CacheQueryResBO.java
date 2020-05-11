package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 缓存查询返回结果DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 12:38
 **/
@Getter
@Setter
@ToString
public class CacheQueryResBO {

    /**
     * value
     */
    private String value;

    /**
     * 有效期，剩余时间
     * 单位:秒
     */
    private Long leftExpireTime;
}
