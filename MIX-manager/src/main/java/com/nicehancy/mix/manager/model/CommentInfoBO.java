package com.nicehancy.mix.manager.model;

import com.nicehancy.mix.manager.model.base.BaseBO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *     评论明细BO
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 17:05
 **/
@Getter
@Setter
@ToString
public class CommentInfoBO extends BaseBO {

    /**
     * 主体id
     */
    private Long subjectId;

    /**
     * 主体类型
     */
    private String subjectType;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headCopy;

    /**
     * 内容
     */
    private String content;

    /**
     * 评论状态
     */
    private String commentStatus;
}
