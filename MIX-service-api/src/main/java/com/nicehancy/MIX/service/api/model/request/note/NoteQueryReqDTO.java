package com.nicehancy.MIX.service.api.model.request.note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 笔记查询请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/13 19:41
 **/
@Getter
@Setter
@ToString
public class NoteQueryReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8875570174281032980L;

    /**
     * 用户名（登录名）
     */
    @NotEmpty(message = "用户名不允许为空")
    @NotNull(message = "用户名不允许为空")
    private String userNo;

    /**
     * 一级目录名
     */
    @NotEmpty(message = "一级目录名不允许为空")
    @NotNull(message = "一级目录名不允许为空")
    private String primaryDirectory;

    /**
     * 文档id
     */
    private String documentId;

    /**
     * 日志ID
     */
    @NotEmpty(message = "日志ID不允许为空")
    @NotNull(message = "日志ID不允许为空")
    private String traceLogId;
}
