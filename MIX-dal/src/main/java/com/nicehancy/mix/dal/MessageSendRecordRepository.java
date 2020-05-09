package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.MessageSendRecordDO;
import com.nicehancy.mix.dal.model.MessageSendRecordPageQueryDO;
import java.util.List;

/**
 * 消息发送记录repository
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/7 17:41
 **/
public interface MessageSendRecordRepository {

    /**
     * 新增消息发送记录
     * @param messageSendRecordDO           DO
     */
    void insert(MessageSendRecordDO messageSendRecordDO);

    /**
     * 消息发送历史记录分页查询
     * @param messageSendRecordPageQueryDO  DO
     * @return                              分页结果
     */
    List<MessageSendRecordDO> pageQuery(MessageSendRecordPageQueryDO messageSendRecordPageQueryDO);

    /**
     * 查询总条数
     * @param pageQueryDO                   DO
     * @return                              条目数
     */
    int queryCount(MessageSendRecordPageQueryDO pageQueryDO);
}
