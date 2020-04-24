package com.nicehancy.MIX.service.api.model.request.note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import java.io.Serializable;

/**
 * 笔记保存请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/13 19:41
 **/
@Getter
@Setter
@ToString
public class NoteSaveReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -123542373045098573L;

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
     * 二级目录
     */
    private String secondaryDirectory;

    /**
     * 文档名
     */
    @NotEmpty(message = "文档名不允许为空")
    @NotNull(message = "文档名不允许为空")
    private String documentName;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 日志ID
     */
    @NotEmpty(message = "日志ID不允许为空")
    @NotNull(message = "日志ID不允许为空")
    private String traceLogId;
}
