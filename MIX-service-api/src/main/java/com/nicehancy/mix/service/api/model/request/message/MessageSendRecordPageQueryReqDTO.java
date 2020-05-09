package com.nicehancy.mix.service.api.model.request.message;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 10:43
 **/
@Getter
@Setter
@ToString
public class MessageSendRecordPageQueryReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = 4577460857951678742L;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条目数
     */
    private Integer pageSize;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 起始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;
}
