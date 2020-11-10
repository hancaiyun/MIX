package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.enums.CommentStatusEnum;
import com.nicehancy.mix.common.enums.FileStatusEnum;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.CommentInfoRepository;
import com.nicehancy.mix.dal.model.AccountInfoDO;
import com.nicehancy.mix.dal.model.CommentInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.regex.Pattern;

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
     * @return                  某主体下评论数
     */
    @Override
    public int queryCountBySubjectId(Long subjectId) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("subjectId").is(subjectId);
        criteria.and("commentStatus").ne(CommentStatusEnum.DISABLE.getCode());
        query.addCriteria(criteria);

        //查询
        long count = mongoTemplate.count(query, CommentInfoDO.class);

        return (int) count;
    }
}
