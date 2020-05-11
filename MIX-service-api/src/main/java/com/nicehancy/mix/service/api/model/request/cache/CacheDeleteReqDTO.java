package com.nicehancy.mix.service.api.model.request.cache;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 13:50
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class CacheDeleteReqDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 409624629420961932L;

    /**
     * key
     */
    @NotNull(message = "key值不允许为空")
    @NotBlank(message = "key值不允许为空")
    private String key;
}
