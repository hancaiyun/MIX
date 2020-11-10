package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.CommentInfoDO;
import com.nicehancy.mix.dal.model.request.CommentInfoPageQueryReqDO;

import java.util.List;

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
     * @param subjectType       主体类型
     * @return                  评论数查询
     */
    int queryCountBySubjectId(Long subjectId, String subjectType);

    /**
     * 评论总条目数查询
     * @param reqDO             分页请求DO
     * @return                  条目数
     */
    int queryCount(CommentInfoPageQueryReqDO reqDO);

    /**
     * 评论列表查询
     * @param reqDO             分页请求DO
     * @return                  分页列表
     */
    List<CommentInfoDO> queryByPage(CommentInfoPageQueryReqDO reqDO);
}
