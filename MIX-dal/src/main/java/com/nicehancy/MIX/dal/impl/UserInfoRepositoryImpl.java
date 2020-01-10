package com.nicehancy.MIX.dal.impl;

import com.nicehancy.MIX.common.utils.UUIDUtil;
import com.nicehancy.MIX.dal.UserInfoRepository;
import com.nicehancy.MIX.dal.model.UserInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 用户信息表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/8 9:28
 **/
@Slf4j
@Repository(value = "userInfoRepositoryImpl")
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询用户信息
     * @param userNo       用户名
     * @return
     */
    @Override
    public UserInfoDO queryByUserNo(String userNo) {

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(userNo);
        query.addCriteria(criteria);

        return mongoTemplate.findOne(query, UserInfoDO.class);
    }

    /**
     * 新增用户
     * @param userInfoDO        用户信息
     * @return
     */
    @Override
    public void addUser(UserInfoDO userInfoDO) {

        //字段初始化
        userInfoDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        //暂新用户默认为用户角色USER
        userInfoDO.setAuthCode("USER");
        userInfoDO.setUpdatedAt(new Date());
        userInfoDO.setCreatedAt(new Date());

        mongoTemplate.insert(userInfoDO);
    }

    /**
     * 更新密码
     * @param userNo            用户名
     * @param encodePassword    密码密文
     */
    @Override
    public void resetPassword(String userNo, String encodePassword) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(userNo);
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        update.set("password", encodePassword);
        update.set("updatedAt", new Date());
        update.set("updatedBy", userNo);

        //更新操作
        mongoTemplate.updateFirst(query, update, UserInfoDO.class);
    }
}
