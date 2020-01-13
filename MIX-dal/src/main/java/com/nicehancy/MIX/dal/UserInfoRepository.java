package com.nicehancy.MIX.dal;

import com.nicehancy.MIX.dal.model.UserInfoDO;

/**
 * 用户管理Repository
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 20:13
 **/
public interface UserInfoRepository {

    /**
     * 根据用户账号查询用户信息
     * @param userNo            用户账号
     * @return                  用户信息
     */
    UserInfoDO queryByUserNo(String userNo);

    /**
     * 新增用户
     * @param userInfoDO        用户信息
     */
    void addUser(UserInfoDO userInfoDO);

    /**
     * 修改密码
     * @param userNo            用户名
     * @param encodePassword    密码密文
     */
    void resetPassword(String userNo, String encodePassword);

    /**
     * 用户基本信息更新
     * @param userInfoDO        用户信息
     */
    void updateUserInfo(UserInfoDO userInfoDO);
}
