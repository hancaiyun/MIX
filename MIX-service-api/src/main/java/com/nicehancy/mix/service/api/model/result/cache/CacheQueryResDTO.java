package com.nicehancy.mix.service.api.model.result.cache;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

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
public class CacheQueryResDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 4057359150115032320L;

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
