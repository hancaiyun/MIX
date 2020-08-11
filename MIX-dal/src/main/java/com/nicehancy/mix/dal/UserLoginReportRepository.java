package com.nicehancy.mix.dal;


import com.nicehancy.mix.dal.model.UserLoginReportDO;

/**
 * 用户登录日报表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 16:08
 **/
public interface UserLoginReportRepository {

    /**
     * 新增登录记录日报表
     * @param userLoginReportDO             登录记录日报表DO
     */
    void insert(UserLoginReportDO userLoginReportDO);
}
