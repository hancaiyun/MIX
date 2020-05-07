package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.dal.MessageSendRecordRepository;
import com.nicehancy.mix.dal.model.MessageSendRecordDO;
import com.nicehancy.mix.dal.model.MessageSendRecordPageQueryDO;
import org.springframework.stereotype.Repository;
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
    @Override
    public void insert(MessageSendRecordDO messageSendRecordDO) {

    }

    @Override
    public List<MessageSendRecordDO> pageQuery(MessageSendRecordPageQueryDO messageSendRecordPageQueryDO) {
         //       //查询条件
         //       Query query = new Query();

         //       //设置查询根据分片时间降序
         //       query.with(new Sort(Sort.Direction.DESC, BillingMerchantInfoKey.SHARED_DATE));

         //       //设置分页查询条件
         //       setConditions(query, billingMerchantInfoPageDO);
         //       int pageNumber = billingMerchantInfoPageDO.getCurrentPage();
         //       int pageSize = billingMerchantInfoPageDO.getPageSize();
         //       query.skip((pageNumber - 1) * pageSize).limit(pageSize);

         //       return mongoTemplate.find(query, BillingMerchantInfoDO.class, CollectionUtil.getBillingCollectionName( *
        //       billingMerchantInfoPageDO.getStartDate()));
        return null;
    }
}
