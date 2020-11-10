package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 *     评论信息DO
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 14:34
 **/
@Getter
@Setter
@ToString
@Document("c_comment_info")
public class CommentInfoDO extends BaseDO {

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
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String headCopy;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 状态
     */
    private String commentStatus;
}
