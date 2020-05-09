package com.nicehancy.mix.service.convert.message;

import com.nicehancy.mix.manager.model.MessageSendRecordBO;
import com.nicehancy.mix.manager.model.MessageSendRecordPageQueryBO;
import com.nicehancy.mix.service.api.model.request.message.MessageSendRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.message.MessageSendRecordDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息DTO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 10:57
 **/
public class MessageDTOConvert {

    private MessageDTOConvert(){
    }

    /**
     * DTO 2 BO
     * @param recordPageQueryReqDTO     DTO
     * @return                          BO
     */
    public static MessageSendRecordPageQueryBO getBOByDTO(MessageSendRecordPageQueryReqDTO recordPageQueryReqDTO) {

        if (recordPageQueryReqDTO == null) {
            return null;
        }

        MessageSendRecordPageQueryBO messageSendRecordPageQueryBO = new MessageSendRecordPageQueryBO();
        messageSendRecordPageQueryBO.setCurrentPage(recordPageQueryReqDTO.getCurrentPage());
        messageSendRecordPageQueryBO.setPageSize(recordPageQueryReqDTO.getPageSize());
        messageSendRecordPageQueryBO.setRecipient(recordPageQueryReqDTO.getRecipient());
        messageSendRecordPageQueryBO.setMessageType(recordPageQueryReqDTO.getMessageType());
        messageSendRecordPageQueryBO.setStartDate(recordPageQueryReqDTO.getStartDate());
        messageSendRecordPageQueryBO.setEndDate(recordPageQueryReqDTO.getEndDate());

        return messageSendRecordPageQueryBO;
    }

    /**
     * BO 2 DTO
     * @param messageSendRecordBO       BO
     * @return                          DTO
     */
    public static MessageSendRecordDTO getDTOByBO(MessageSendRecordBO messageSendRecordBO) {

        if (messageSendRecordBO == null) {
            return null;
        }

        MessageSendRecordDTO messageSendRecordDTO = new MessageSendRecordDTO();
        messageSendRecordDTO.setMessageId(messageSendRecordBO.getMessageId());
        messageSendRecordDTO.setMessageType(messageSendRecordBO.getMessageType());
        messageSendRecordDTO.setMessageName(messageSendRecordBO.getMessageName());
        messageSendRecordDTO.setSender(messageSendRecordBO.getSender());
        messageSendRecordDTO.setRecipient(messageSendRecordBO.getRecipient());
        messageSendRecordDTO.setSendResult(messageSendRecordBO.getSendResult());
        messageSendRecordDTO.setReason(messageSendRecordBO.getReason());
        messageSendRecordDTO.setContent(messageSendRecordBO.getContent());
        messageSendRecordDTO.setCreatedAt(messageSendRecordBO.getCreatedAt());
        messageSendRecordDTO.setCreatedBy(messageSendRecordBO.getCreatedBy());
        messageSendRecordDTO.setUpdatedAt(messageSendRecordBO.getUpdatedAt());
        messageSendRecordDTO.setUpdatedBy(messageSendRecordBO.getUpdatedBy());

        return messageSendRecordDTO;
    }

    /**
     * BOS 2 DTOS
     * @param list      BOS
     * @return          DTOS
     */
    public static List<MessageSendRecordDTO> getDTOSByBOS(List<MessageSendRecordBO> list) {

        if(null == list){
            return null;
        }

        List<MessageSendRecordDTO> arrayList = new ArrayList<>();
        for(MessageSendRecordBO recordBO : list){
            arrayList.add(getDTOByBO(recordBO));
        }
        return arrayList;
    }
}
