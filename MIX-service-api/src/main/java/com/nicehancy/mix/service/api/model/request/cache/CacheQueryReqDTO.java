package com.nicehancy.mix.service.api.model.request.cache;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * 缓存查询请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 13:39
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class CacheQueryReqDTO extends BaseReqDTO {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -6515559633743760774L;

    /**
     * key
     */
    @NotNull(message = "key值不允许为空")
    @NotBlank(message = "key值不允许为空")
    private String key;
}
