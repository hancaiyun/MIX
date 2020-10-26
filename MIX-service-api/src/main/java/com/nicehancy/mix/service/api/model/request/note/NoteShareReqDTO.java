package com.nicehancy.mix.service.api.model.request.note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.MemberOf;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * 文件共享请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/27 17:33
 **/
@Getter
@Setter
@ToString
public class NoteShareReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -7840694716062275672L;

    /**
     * 文件id
     */
    private Long id;

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
     * 分享标识
     */
    @NotEmpty(message = "分享标识不允许为空")
    @NotNull(message = "分享标识不允许为空")
    @MemberOf(value={"TRUE", "FALSE"}, message = "分享标识有误")
    private String shareFlag;

    /**
     * 日志ID
     */
    @NotEmpty(message = "日志ID不允许为空")
    @NotNull(message = "日志ID不允许为空")
    private String traceLogId;
}
