package com.nicehancy.mix.service.api.model.request.note;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.MemberOf;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * <p>
 *     评论提交接口
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/5 13:50
 **/
@Getter
@Setter
@ToString
public class CommentCommitReqDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 394950012701984557L;

    /**
     * 用户编号
     */
    @NotBlank(message = "用户编号不允许为空")
    @NotNull(message = "用户编号不允许为空")
    private String userNo;

    /**
     * 主体id
     */
    @NotNull(message = "主体id不允许为空")
    private Long subjectId;

    /**
     * 主体类型
     */
    @NotBlank(message = "主体类型不允许为空")
    @NotNull(message = "主体类型不允许为空")
    @MemberOf(value={"NOTE", "COMMENT"}, message = "主体类型错误")
    private String subjectType;

    /**
     * 评论内容
     * 敏感词过滤
     */
    @NotBlank(message = "评论内容不允许为空")
    @NotNull(message = "评论内容不允许为空")
    private String content;
}
