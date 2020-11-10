package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *     评论分页查询接口
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/10 14:35
 **/
@Getter
@Setter
@ToString
public class CommentInfoPageQueryReqBO {

    /**
     * 主体id
     */
    private Long subjectId;

    /**
     * 主体类型
     */
    private String subjectType;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条目数
     */
    private Integer pageSize;
}
