package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *     评论提交请求BO
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/5 13:50
 **/
@Getter
@Setter
@ToString
public class CommentCommitReqBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 主体id
     */
    private Long subjectId;

    /**
     * 主体类型
     */
    private String subjectType;

    /**
     * 评论内容
     * 注：需要敏感词校验
     */
    private String content;
}
