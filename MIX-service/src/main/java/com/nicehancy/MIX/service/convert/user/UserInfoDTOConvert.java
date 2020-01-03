package com.nicehancy.MIX.service.convert.user;


import com.nicehancy.MIX.manager.model.UserInfoBO;
import com.nicehancy.MIX.service.api.model.UserInfoDTO;

import java.util.Date;

/**
 * 用户信息管理类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/8 14:03
 **/
public class UserInfoDTOConvert {

    /**
     * 私有构造函数
     */
    private UserInfoDTOConvert(){
    }

    /**
     * 通过DTO获取BO
     * @param userInfoDTO    DTO
     * @return
     */
    public static UserInfoBO getBOByDTO(UserInfoDTO userInfoDTO) {

        if (userInfoDTO == null) {
            return null;
        }

        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setUserNo(userInfoDTO.getUserNo());
        userInfoBO.setUserName(userInfoDTO.getUserName());
        userInfoBO.setNickName(userInfoDTO.getNickName());
        userInfoBO.setPassword(userInfoDTO.getPassword());
        userInfoBO.setEMail(userInfoDTO.getEMail());
        userInfoBO.setAuthCode(userInfoDTO.getAuthCode());
        userInfoBO.setRemark(userInfoDTO.getRemark());
        userInfoBO.setCreatedAt(new Date());
        userInfoBO.setCreatedBy(userInfoDTO.getUserNo());
        userInfoBO.setUpdatedAt(new Date());
        userInfoBO.setUpdatedBy(userInfoDTO.getUserNo());

        return userInfoBO;
    }
}
