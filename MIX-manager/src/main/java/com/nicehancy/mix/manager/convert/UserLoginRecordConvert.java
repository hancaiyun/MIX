package com.nicehancy.mix.manager.convert;

import com.nicehancy.mix.dal.model.UserLoginRecordDO;
import com.nicehancy.mix.manager.model.UserLoginRecordBO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录记录BO convert
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 17:30
 **/
public class UserLoginRecordConvert {

    /**
     * 私有构造函数
     */
    private UserLoginRecordConvert(){
    }

    /**
     * DO 2 BO
     * @param userLoginRecordDO              DO
     * @return                               BO
     */
    public static UserLoginRecordBO getBOByDO(UserLoginRecordDO userLoginRecordDO) {

        if (userLoginRecordDO == null) {
            return null;
        }

        UserLoginRecordBO userLoginRecordBO = new UserLoginRecordBO();
        userLoginRecordBO.setUserNo(userLoginRecordDO.getUserNo());
        userLoginRecordBO.setLoginTime(userLoginRecordDO.getLoginTime());
        userLoginRecordBO.setLogoutTime(userLoginRecordDO.getLogoutTime());
        userLoginRecordBO.setCreatedAt(userLoginRecordDO.getCreatedAt());
        userLoginRecordBO.setCreatedBy(userLoginRecordDO.getCreatedBy());
        userLoginRecordBO.setUpdatedAt(userLoginRecordDO.getUpdatedAt());
        userLoginRecordBO.setUpdatedBy(userLoginRecordDO.getUpdatedBy());

        return userLoginRecordBO;
    }

    /**
     * DOs 2 BOs
     * @param recordDOList                     DOs
     * @return                                 BOs
     */
    public static List<UserLoginRecordBO> getBOsByDOs(List<UserLoginRecordDO> recordDOList){

        if(CollectionUtils.isEmpty(recordDOList)){
            return null;
        }

        List<UserLoginRecordBO> recordBOList = new ArrayList<>();
        for(UserLoginRecordDO recordDO : recordDOList){
            recordBOList.add(getBOByDO(recordDO));
        }

        return recordBOList;
    }
}
