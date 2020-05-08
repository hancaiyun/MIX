package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.PageQueryBaseDO;
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
public class MessageSendRecordPageQueryDO extends PageQueryBaseDO {

    /**
     * 用户id，用户登陆号
     */
    private String userNo;

    /**
     * 查询起始时间
     */
    private Date startDate;

    /**
     * 查询结束时间
     */
    private Date endDate;
}
