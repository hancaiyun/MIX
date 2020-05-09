package com.nicehancy.mix.manager.convert;

import com.nicehancy.mix.dal.model.MessageSendRecordDO;
import com.nicehancy.mix.dal.model.MessageSendRecordPageQueryDO;
import com.nicehancy.mix.manager.model.MessageSendRecordBO;
import com.nicehancy.mix.manager.model.MessageSendRecordPageQueryBO;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息BO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 11:14
 **/
public class MessageBOConvert {

    private MessageBOConvert(){
    }

    /**
     * BO 2 DO
     * @param pageQueryBO       BO
     * @return                  DO
     */
    public static MessageSendRecordPageQueryDO getDOByBO(MessageSendRecordPageQueryBO pageQueryBO) {

        if (pageQueryBO == null) {
            return null;
        }

        MessageSendRecordPageQueryDO messageSendRecordPageQueryDO = new MessageSendRecordPageQueryDO();
        messageSendRecordPageQueryDO.setCurrentPage(pageQueryBO.getCurrentPage());
        messageSendRecordPageQueryDO.setPageSize(pageQueryBO.getPageSize());
        messageSendRecordPageQueryDO.setStartDate(pageQueryBO.getStartDate());
        messageSendRecordPageQueryDO.setEndDate(pageQueryBO.getEndDate());
        messageSendRecordPageQueryDO.setRecipient(pageQueryBO.getRecipient());
        messageSendRecordPageQueryDO.setMessageType(pageQueryBO.getMessageType());
        
        return messageSendRecordPageQueryDO;
    }

    /**
     * DO 2 BO
     * @param recordDO          DO
     * @return                  BO
     */
    public static MessageSendRecordBO getBOByDO(MessageSendRecordDO recordDO) {

        if (recordDO == null) {
            return null;
        }

        MessageSendRecordBO messageSendRecordBO = new MessageSendRecordBO();
        messageSendRecordBO.setMessageId(recordDO.getMessageId());
        messageSendRecordBO.setMessageType(recordDO.getMessageType());
        messageSendRecordBO.setMessageName(recordDO.getMessageName());
        messageSendRecordBO.setSender(recordDO.getSender());
        messageSendRecordBO.setRecipient(recordDO.getRecipient());
        messageSendRecordBO.setSendResult(recordDO.getSendResult());
        messageSendRecordBO.setReason(recordDO.getReason());
        messageSendRecordBO.setContent(recordDO.getContent());
        messageSendRecordBO.setCreatedAt(recordDO.getCreatedAt());
        messageSendRecordBO.setCreatedBy(recordDO.getCreatedBy());
        messageSendRecordBO.setUpdatedAt(recordDO.getUpdatedAt());
        messageSendRecordBO.setUpdatedBy(recordDO.getUpdatedBy());

        return messageSendRecordBO;
    }

    /**
     * DOS 2 BOS
     * @param pageQuery         DOS
     * @return                  BOS
     */
    public static List<MessageSendRecordBO> getBOSByDOS(List<MessageSendRecordDO> pageQuery) {

        if(null == pageQuery){
            return null;
        }

        List<MessageSendRecordBO> list = new ArrayList<>();
        for(MessageSendRecordDO messageSendRecordDO : pageQuery){
            list.add(getBOByDO(messageSendRecordDO));
        }
        return list;
    }
}
