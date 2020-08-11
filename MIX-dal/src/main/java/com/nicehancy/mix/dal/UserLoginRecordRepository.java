package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.UserLoginRecordDO;

/**
 * 用户登录记录表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 15:57
 **/
public interface UserLoginRecordRepository {

    /**
     * 新增登录记录
     * @param userLoginRecordDO             登录记录DO
     */
    void insert(UserLoginRecordDO userLoginRecordDO);

    /**
     * 更新登录记录-登出时间
     * @param userLoginRecordDO             登录记录DO
     */
    void update(UserLoginRecordDO userLoginRecordDO);
}
