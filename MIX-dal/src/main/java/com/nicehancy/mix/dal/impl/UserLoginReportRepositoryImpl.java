package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.UserLoginReportRepository;
import com.nicehancy.mix.dal.model.UserLoginReportDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;

/**
 * 登录操作记录报表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 16:10
 **/
@Repository(value = "userLoginReportRepositoryImpl")
public class UserLoginReportRepositoryImpl implements UserLoginReportRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增登录报表
     * @param userLoginReportDO             登录记录日报表DO
     */
    @Override
    public void insert(UserLoginReportDO userLoginReportDO) {

        //字段初始化
        userLoginReportDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        userLoginReportDO.setUpdatedAt(new Date());
        userLoginReportDO.setCreatedAt(new Date());

        mongoTemplate.insert(userLoginReportDO);
    }
}
