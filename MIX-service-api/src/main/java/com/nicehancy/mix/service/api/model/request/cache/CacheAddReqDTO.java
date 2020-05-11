package com.nicehancy.mix.service.api.model.request.cache;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import java.io.Serializable;

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
@ToString(callSuper = true)
public class CacheAddReqDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 6386589518548745568L;

    /**
     * key
     */
    @NotNull(message = "key值不允许为空")
    @NotBlank(message = "key值不允许为空")
    private String key;

    /**
     * value
     */
    @NotNull(message = "value值不允许为空")
    @NotBlank(message = "value值不允许为空")
    private String value;

    /**
     * 有效期
     * 没有则无有效期
     */
    private Long expireTime;
}
