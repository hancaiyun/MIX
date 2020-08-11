package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.UserLoginRecordRepository;
import com.nicehancy.mix.dal.model.UserInfoDO;
import com.nicehancy.mix.dal.model.UserLoginRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import java.util.Date;

/**
 * 用户登录记录表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 16:11
 **/
@Repository(value = "userLoginRecordRepositoryImpl")
public class UserLoginRecordRepositoryImpl implements UserLoginRecordRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 登录记录新增
     * @param userLoginRecordDO             登录记录DO
     */
    @Override
    public void insert(UserLoginRecordDO userLoginRecordDO) {

        //字段初始化
        userLoginRecordDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        userLoginRecordDO.setUpdatedAt(new Date());
        userLoginRecordDO.setCreatedAt(new Date());

        mongoTemplate.insert(userLoginRecordDO);
    }

    /**
     * 登录信息更新
     * @param userLoginRecordDO             登录记录DO
     */
    @Override
    public void update(UserLoginRecordDO userLoginRecordDO) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(userLoginRecordDO.getUserNo());
        criteria.and("logoutTime").is(null);
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        update.set("logoutTime", userLoginRecordDO.getLogoutTime());
        update.set("updatedAt", new Date());
        update.set("updatedBy", CommonConstant.SYSTEM);

        //更新操作
        mongoTemplate.updateFirst(query, update, UserLoginRecordDO.class);
    }
}
