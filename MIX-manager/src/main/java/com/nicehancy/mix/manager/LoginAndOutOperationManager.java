package com.nicehancy.mix.manager;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.dal.UserLoginRecordRepository;
import com.nicehancy.mix.dal.model.UserLoginRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 登录登出成功后处理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 15:25
 **/
@Component
public class LoginAndOutOperationManager {

    @Autowired
    private UserLoginRecordRepository userLoginRecordRepository;

    /**
     * 登录成功后处理
     * @param userNo            用户编号
     */
    public void loginOperation(String userNo){

        //1、新增登录记录
        UserLoginRecordDO userLoginRecordDO = new UserLoginRecordDO();
        userLoginRecordDO.setUserNo(userNo);
        userLoginRecordDO.setLoginTime(DateUtil.formatToDate(new Date(), DatePatternConstant.STANDARD_PATTERN));
        userLoginRecordDO.setCreatedBy(CommonConstant.SYSTEM);
        userLoginRecordDO.setUpdatedBy(CommonConstant.SYSTEM);
        userLoginRecordRepository.insert(userLoginRecordDO);

        //TODO 2、更新用户登录状态-在线
    }

    /**
     * 登出成功后处理
     * @param userNo            用户编号
     */
    public void logoutOperation(String userNo){

        //TODO 1、更新登出时间
        UserLoginRecordDO userLoginRecordDO = new UserLoginRecordDO();
        userLoginRecordDO.setUserNo(userNo);
        userLoginRecordDO.setLogoutTime(DateUtil.formatToDate(new Date(), DatePatternConstant.STANDARD_PATTERN));
        userLoginRecordRepository.update(userLoginRecordDO);

        //TODO 2、更新用户登录状态-离线
    }
}
