package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 缓存新增请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 13:49
 **/
@Getter
@Setter
@ToString
public class CacheAddReqBO {

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    /**
     * 有效期
     * 没有则无有效期
     */
    private Long expireTime;
}
