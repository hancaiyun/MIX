package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/7 17:55
 **/
@Getter
@Setter
@ToString
public class MessageSendRecordPageQueryBO {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条目数
     */
    private Integer pageSize;

    /**
     * 查询起始时间
     */
    private Date startDate;

    /**
     * 查询结束时间
     */
    private Date endDate;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 消息类型
     */
    private String messageType;
}
