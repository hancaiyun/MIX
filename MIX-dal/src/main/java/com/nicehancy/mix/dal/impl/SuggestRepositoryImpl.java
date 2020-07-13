package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.SuggestRepository;
import com.nicehancy.mix.dal.model.SuggestInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 用户反馈
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/13 18:18
 **/
@Repository(value = "suggestRepositoryImpl")
public class SuggestRepositoryImpl implements SuggestRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增反馈
     * @param userNo        用户编号
     * @param suggestion    反馈内容
     */
    @Override
    public void commit(String userNo, String suggestion) {

        SuggestInfoDO suggestInfoDO = new SuggestInfoDO();
        suggestInfoDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        suggestInfoDO.setUserNo(userNo);
        suggestInfoDO.setSuggestion(suggestion);
        suggestInfoDO.setCreatedAt(new Date());
        suggestInfoDO.setCreatedBy(userNo);
        suggestInfoDO.setUpdatedAt(new Date());
        suggestInfoDO.setUpdatedBy(userNo);

        mongoTemplate.insert(suggestInfoDO);
    }
}
