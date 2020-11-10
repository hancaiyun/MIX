package com.nicehancy.mix.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *     评论明细DTO
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 17:05
 **/
@Getter
@Setter
@ToString
public class CommentInfoDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8584346218025045101L;

    /**
     * 数据库id
     */
    private Long id;

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

    /**
     * 更新时间
     */
    private String updatedAt;
}
