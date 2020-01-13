package com.nicehancy.MIX.manager.convert;


import com.nicehancy.MIX.dal.model.UserInfoDO;
import com.nicehancy.MIX.manager.model.UserInfoBO;

/**
 *
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/8 9:08
 **/
public class UserInfoBOConvert {

    /**
     * 私有化构造函数
     */
    private UserInfoBOConvert(){
    }

    /**
     * 用户信息DO转BO
     * @param userInfoDO    DO
     * @return              BO
     */
    public static UserInfoBO getBOByDO(UserInfoDO userInfoDO) {

        if (userInfoDO == null) {
            return null;
        }

        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setLoginNo(userInfoDO.getUserNo());
        userInfoBO.setNickName(userInfoDO.getNickName());
        userInfoBO.setHeadCopy(userInfoDO.getHeadCopy());
        userInfoBO.setPassword(userInfoDO.getPassword());
        userInfoBO.setEmail(userInfoDO.getEmail());
        userInfoBO.setAuthCode(userInfoDO.getAuthCode());
        userInfoBO.setStatus(userInfoDO.getStatus());
        userInfoBO.setRemark(userInfoDO.getRemark());
        userInfoBO.setCreatedAt(userInfoDO.getCreatedAt());
        userInfoBO.setCreatedBy(userInfoDO.getCreatedBy());
        userInfoBO.setUpdatedAt(userInfoDO.getUpdatedAt());
        userInfoBO.setUpdatedBy(userInfoDO.getUpdatedBy());

        return userInfoBO;
    }

    /**
     * 根据BO获取DO
     * @param userInfoBO        BO
     * @return                  DO
     */
    public static UserInfoDO getDOByBO(UserInfoBO userInfoBO) {

        if (userInfoBO == null) {
            return null;
        }

        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserNo(userInfoBO.getLoginNo());
        userInfoDO.setNickName(userInfoBO.getNickName());
        userInfoDO.setPassword(userInfoBO.getPassword());
        userInfoDO.setAuthCode(userInfoBO.getAuthCode());
        userInfoDO.setEmail(userInfoBO.getEmail());
        userInfoDO.setRemark(userInfoBO.getRemark());
        userInfoDO.setCreatedAt(userInfoBO.getCreatedAt());
        userInfoDO.setCreatedBy(userInfoBO.getCreatedBy());
        userInfoDO.setUpdatedAt(userInfoBO.getUpdatedAt());
        userInfoDO.setUpdatedBy(userInfoBO.getUpdatedBy());

        return userInfoDO;
    }
}
