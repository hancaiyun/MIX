package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.CommentInfoDO;

/**
 * <p>
 *    文档评论表repository
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 14:26
 **/
public interface CommentInfoRepository {

    /**
     * 新增评论
     * @param commentInfoDO     DO
     */
    void insert(CommentInfoDO commentInfoDO);

    /**
     * 评论数查询
     * @param subjectId         主体id
     * @return                  评论数查询
     */
    int queryCountBySubjectId(Long subjectId);
}
