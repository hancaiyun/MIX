package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.PageQueryBaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}
