package com.nicehancy.MIX.service.api.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * 公共请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 12:35
 **/
@Getter
@Setter
@ToString
public class BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 7242453255099488123L;

    /**
     * 日志ID
     */
    @NotNull(message = "日志ID不允许为空")
    @NotBlank(message = "日志ID不允许为空")
    private String traceLogId;

    /**
     * 请求系统
     */
    @NotNull(message = "请求系统不允许为空")
    @NotBlank(message = "请求系统不允许为空")
    private String requestSystem;
}
