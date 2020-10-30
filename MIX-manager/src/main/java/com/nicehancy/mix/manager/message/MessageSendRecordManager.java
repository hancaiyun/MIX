package com.nicehancy.mix.manager.message;

import com.nicehancy.mix.dal.MessageSendRecordRepository;
import com.nicehancy.mix.manager.convert.MessageBOConvert;
import com.nicehancy.mix.manager.model.MessageSendRecordBO;
import com.nicehancy.mix.manager.model.MessageSendRecordPageQueryBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息发送记录manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 10:59
 **/
@Component
public class MessageSendRecordManager {

    @Autowired
    private MessageSendRecordRepository messageSendRecordRepository;

    /**
     * 分页查询消息发送记录
     * @param boByDTO               BO
     * @return                      分页查询结果
     */
    public List<MessageSendRecordBO> pageQuery(MessageSendRecordPageQueryBO boByDTO) {

        return MessageBOConvert.getBOSByDOS(messageSendRecordRepository.pageQuery(MessageBOConvert.getDOByBO(boByDTO)));
    }

    /**
     * 查询总条数
     * @param pageQueryBO           BO
     * @return                      总条数
     */
    public int queryCount(MessageSendRecordPageQueryBO pageQueryBO) {

        return messageSendRecordRepository.queryCount(MessageBOConvert.getDOByBO(pageQueryBO));
    }
}
