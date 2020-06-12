package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.MessageSendRecordRepository;
import com.nicehancy.mix.dal.model.MessageSendRecordDO;
import com.nicehancy.mix.dal.model.MessageSendRecordPageQueryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 消息发送记录表操作
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/7 17:44
 **/
@Repository(value = "messageSendRecordRepositoryImpl")
public class MessageSendRecordRepositoryImpl implements MessageSendRecordRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增消息发送记录
     * @param messageSendRecordDO   DO
     */
    @Override
    public void insert(MessageSendRecordDO messageSendRecordDO) {

        //字段初始化
        messageSendRecordDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        messageSendRecordDO.setCreatedAt(new Date());
        messageSendRecordDO.setUpdatedAt(new Date());

        mongoTemplate.insert(messageSendRecordDO);
    }

    /**
     * 消息发送记录分页查询
     * @param pageQueryDO                   DO
     * @return                              分页查询结果
     */
    @Override
    public List<MessageSendRecordDO> pageQuery(MessageSendRecordPageQueryDO pageQueryDO) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(pageQueryDO.getMessageType())){
            criteria.and("messageType").is(pageQueryDO.getMessageType());
        }
        if(!StringUtils.isEmpty(pageQueryDO.getRecipient())){
            criteria.and("recipient").is(pageQueryDO.getRecipient());
        }
        if(null != pageQueryDO.getStartDate() && null != pageQueryDO.getEndDate()){
            criteria.and("createdAt").gte(pageQueryDO.getStartDate()).lt(DateUtil.addDate(pageQueryDO.getEndDate(),
                    Calendar.DATE, 1));
        }

        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //分页
        int pageNumber = pageQueryDO.getCurrentPage();
        int pageSize = pageQueryDO.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, MessageSendRecordDO.class);
    }

    /**
     * 查询总条数
     * @param pageQueryDO                   DO
     * @return                              条目数
     */
    @Override
    public int queryCount(MessageSendRecordPageQueryDO pageQueryDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(pageQueryDO.getMessageType())){
            criteria.and("messageType").is(pageQueryDO.getMessageType());
        }
        if(!StringUtils.isEmpty(pageQueryDO.getRecipient())){
            criteria.and("recipient").is(pageQueryDO.getRecipient());
        }
        if(null != pageQueryDO.getStartDate() && null != pageQueryDO.getEndDate()){
            criteria.and("createdAt").gte(pageQueryDO.getStartDate()).lt(DateUtil.addDate(pageQueryDO.getEndDate(),
                    Calendar.DATE, 1));
        }
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //查询
        long count = mongoTemplate.count(query, MessageSendRecordDO.class);

        return (int) count;
    }
}
