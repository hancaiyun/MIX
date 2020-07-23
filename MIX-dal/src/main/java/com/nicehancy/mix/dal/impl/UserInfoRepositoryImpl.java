package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.UserInfoRepository;
import com.nicehancy.mix.dal.model.UserInfoDO;
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
        //状态为可用
        userInfoDO.setStatus("USABLE");
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

    /**
     * 更新用户基本信息
     * @param userInfoDO        用户信息
     */
    @Override
    public void updateUserInfo(UserInfoDO userInfoDO) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(userInfoDO.getUserNo());
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        update.set("nickName", userInfoDO.getNickName());
        update.set("headCopy", userInfoDO.getHeadCopy());
        update.set("email", userInfoDO.getEmail());
        update.set("remark", userInfoDO.getRemark());
        update.set("updatedAt", new Date());
        update.set("updatedBy", userInfoDO.getUserNo());

        //更新操作
        mongoTemplate.updateFirst(query, update, UserInfoDO.class);
    }
}
