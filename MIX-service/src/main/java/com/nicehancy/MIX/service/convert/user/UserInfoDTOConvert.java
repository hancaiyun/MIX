package com.nicehancy.MIX.service.convert.user;


import com.nicehancy.MIX.manager.model.UserInfoBO;
import com.nicehancy.MIX.service.api.model.UserInfoDTO;

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
     * @return               BO
     */
    public static UserInfoBO getBOByDTO(UserInfoDTO userInfoDTO) {

        if (userInfoDTO == null) {
            return null;
        }

        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setLoginNo(userInfoDTO.getLoginNo());
        userInfoBO.setNickName(userInfoDTO.getNickName());
        userInfoBO.setPassword(userInfoDTO.getPassword());
        userInfoBO.setAuthCode(userInfoDTO.getAuthCode());
        userInfoBO.setEmail(userInfoDTO.getEmail());
        userInfoBO.setRemark(userInfoDTO.getRemark());

        return userInfoBO;
    }
}
