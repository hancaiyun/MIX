package com.nicehancy.mix.dal.impl;

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
     * @param messageSendRecordPageQueryDO  DO
     * @return                              分页查询结果
     */
    @Override
    public List<MessageSendRecordDO> pageQuery(MessageSendRecordPageQueryDO messageSendRecordPageQueryDO) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(messageSendRecordPageQueryDO.getUserNo());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //分页
        int pageNumber = messageSendRecordPageQueryDO.getCurrentPage();
        int pageSize = messageSendRecordPageQueryDO.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, MessageSendRecordDO.class);
    }
}
