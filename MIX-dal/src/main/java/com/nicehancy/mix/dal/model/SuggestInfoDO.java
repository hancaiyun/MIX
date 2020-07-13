package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户反馈信息DO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/13 18:19
 **/
@Getter
@Setter
@ToString
@Document("c_suggest_info")
public class SuggestInfoDO extends BaseDO{

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 建议内容
     */
    private String suggestion;
}
