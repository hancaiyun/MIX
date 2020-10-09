package com.nicehancy.mix.service.api.model.request.note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.MemberOf;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * 文件共享查询请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/27 17:33
 **/
@Getter
@Setter
@ToString
public class NoteShareQueryReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 3387751832039539097L;

    /**
     * 用户名（登录名）
     */
    @NotEmpty(message = "用户名不允许为空")
    @NotNull(message = "用户名不允许为空")
    private String userNo;

    /**
     * 日志ID
     */
    @NotEmpty(message = "日志ID不允许为空")
    @NotNull(message = "日志ID不允许为空")
    private String traceLogId;
}
