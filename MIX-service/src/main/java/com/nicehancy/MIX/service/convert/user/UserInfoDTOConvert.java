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
        userInfoBO.setHeadCopy(userInfoDTO.getHeadCopy());
        userInfoBO.setPassword(userInfoDTO.getPassword());
        userInfoBO.setAuthCode(userInfoDTO.getAuthCode());
        userInfoBO.setEmail(userInfoDTO.getEmail());
        userInfoBO.setRemark(userInfoDTO.getRemark());

        return userInfoBO;
    }

    /**
     * 用户信息BO转DTO
     * @param user          BO
     * @return              DTO
     */
    public static UserInfoDTO getDTOByBO(UserInfoBO user) {

        if (user == null) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setLoginNo(user.getLoginNo());
        userInfoDTO.setNickName(user.getNickName());
        userInfoDTO.setHeadCopy(user.getHeadCopy());
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setPassword(user.getPassword());
        userInfoDTO.setAuthCode(user.getAuthCode());
        userInfoDTO.setStatus(user.getStatus());
        userInfoDTO.setRemark(user.getRemark());

        return userInfoDTO;
    }
}
