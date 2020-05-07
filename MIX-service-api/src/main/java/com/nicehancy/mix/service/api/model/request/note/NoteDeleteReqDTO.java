package com.nicehancy.mix.service.api.model.request.note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * 笔记删除请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/13 19:41
 **/
@Getter
@Setter
@ToString
public class NoteDeleteReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -2165705747772457663L;

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
     * 二级目录名
     */
    private String secondaryDirectory;

    /**
     * 文档名称
     */
    @NotEmpty(message = "笔记名不允许为空")
    @NotNull(message = "笔记名不允许为空")
    private String documentName;

    /**
     * 日志ID
     */
    @NotEmpty(message = "日志ID不允许为空")
    @NotNull(message = "日志ID不允许为空")
    private String traceLogId;
}
