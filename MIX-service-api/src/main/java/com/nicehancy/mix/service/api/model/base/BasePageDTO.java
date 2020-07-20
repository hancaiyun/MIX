package com.nicehancy.mix.service.api.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.*;

import java.io.Serializable;

/**
 * 分页基础父类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/20 18:18
 **/
@Getter
@Setter
@ToString
public class BasePageDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 7777122782306002038L;

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

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @MatchPattern(pattern = "^\\d*$", message = "当前页请输入整数")
    @Min(value=1, message="当前页不能小于1")
    private Integer currentPage;

    /**
     * 每页条目数
     */
    @NotNull(message = "每页显示数量不能为空")
    @MatchPattern(pattern = "^\\d*$", message = "每页显示数量请输入整数")
    @Max(value=100, message="每页显示数量不能大于100")
    @Min(value=1, message="每页显示数量不能小于1")
    private Integer pageSize;
}
