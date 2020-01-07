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
public class NoteManageReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 3014263504502894983L;

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
    private String documentName;

    /**
     * 操作类型
     * 增加-ADD
     * 删除-DELETE
     */
    @NotEmpty(message = "操作类型不允许为空")
    @NotNull(message = "操作类型不允许为空")
    private String operatorType;

    /**
     * 日志ID
     */
    @NotEmpty(message = "日志ID不允许为空")
    @NotNull(message = "日志ID不允许为空")
    private String traceLogId;
}
