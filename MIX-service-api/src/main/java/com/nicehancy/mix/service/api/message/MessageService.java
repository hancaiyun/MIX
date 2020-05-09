package com.nicehancy.mix.service.api.message;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.message.MessageSendRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.request.message.MessageSendReqDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.model.result.message.MessageSendRecordDTO;
import java.util.List;

/**
 * 消息管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 10:40
 **/
public interface MessageService {

    /**
     * 消息发送记录分页查询接口
     * @param pageQueryReqDTO       分页请求参数
     * @return                      分页结果
     */
    Result<BasePageQueryResDTO<MessageSendRecordDTO>> pageQuery(MessageSendRecordPageQueryReqDTO pageQueryReqDTO);

    /**
     * 消息手动补发
     * @param messageSendReqDTO     消息发送请求DTO
     * @return                      发送结果
     */
    Result<Boolean> send(MessageSendReqDTO messageSendReqDTO);
}
