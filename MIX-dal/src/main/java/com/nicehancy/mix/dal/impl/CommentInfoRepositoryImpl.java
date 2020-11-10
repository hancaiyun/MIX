package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.enums.CommentStatusEnum;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.CommentInfoRepository;
import com.nicehancy.mix.dal.model.CommentInfoDO;
import com.nicehancy.mix.dal.model.request.CommentInfoPageQueryReqDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *    文档信息表
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 14:28
 **/
@Repository(value = "commentInfoRepositoryImpl")
public class CommentInfoRepositoryImpl implements CommentInfoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     * @param commentInfoDO     DO
     */
    @Override
    public void insert(CommentInfoDO commentInfoDO) {

        //字段初始化
        commentInfoDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        commentInfoDO.setCreatedAt(new Date());
        commentInfoDO.setUpdatedAt(new Date());
        commentInfoDO.setCommentStatus(CommentStatusEnum.ENABLE.getCode());

        mongoTemplate.insert(commentInfoDO);
    }

    /**
     * 评论数查询
     * @param subjectId         主体id
     * @param subjectType       主体类型
     * @return                  某主体下评论数
     */
    @Override
    public int queryCountBySubjectId(Long subjectId, String subjectType) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("subjectId").is(subjectId);
        criteria.and("subjectType").is(subjectType);
        criteria.and("commentStatus").ne(CommentStatusEnum.DISABLE.getCode());
        query.addCriteria(criteria);

        //查询
        long count = mongoTemplate.count(query, CommentInfoDO.class);

        return (int) count;
    }

    /**
     * 分页总条目数查询
     * @param reqDO             分页请求DO
     * @return                  总条目数
     */
    @Override
    public int queryCount(CommentInfoPageQueryReqDO reqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("subjectId").is(reqDO.getSubjectId());
        criteria.and("subjectType").is(reqDO.getSubjectType());
        criteria.and("commentStatus").ne(CommentStatusEnum.DISABLE.getCode());
        query.addCriteria(criteria);

        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));

        //查询
        long count = mongoTemplate.count(query, CommentInfoDO.class);

        return (int) count;
    }

    /**
     * 分页列表查询
     * @param reqDO             分页请求DO
     * @return                  分页列表
     */
    @Override
    public List<CommentInfoDO> queryByPage(CommentInfoPageQueryReqDO reqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("subjectId").is(reqDO.getSubjectId());
        criteria.and("subjectType").is(reqDO.getSubjectType());
        criteria.and("commentStatus").ne(CommentStatusEnum.DISABLE.getCode());
        query.addCriteria(criteria);

        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));

        //分页
        //分页
        int pageNumber = reqDO.getCurrentPage();
        int pageSize = reqDO.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);

        //查询
        return mongoTemplate.find(query, CommentInfoDO.class);
    }
}
