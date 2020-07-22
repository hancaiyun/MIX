package com.nicehancy.mix.manager.convert;

import com.nicehancy.mix.dal.model.AccountInfoDO;
import com.nicehancy.mix.dal.model.request.AccountDeleteReqDO;
import com.nicehancy.mix.dal.model.request.AccountQueryReqDO;
import com.nicehancy.mix.manager.model.AccountAddReqBO;
import com.nicehancy.mix.manager.model.AccountDeleteReqBO;
import com.nicehancy.mix.manager.model.AccountInfoBO;
import com.nicehancy.mix.manager.model.AccountQueryReqBO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号管理BO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/21 12:11
 **/
public class AccountInfoBOConvert {

    /**
     * 私有构造函数
     */
    private AccountInfoBOConvert(){
    }

    /**
     * 根据请求BO获取DO
     * @param accountAddReqBO       BO
     * @return                      DO
     */
    public static AccountInfoDO getDOByBO(AccountAddReqBO accountAddReqBO) {

        if (accountAddReqBO == null) {
            return null;
        }

        AccountInfoDO accountInfoDO = new AccountInfoDO();
        accountInfoDO.setUserNo(accountAddReqBO.getUserNo());
        accountInfoDO.setAddress(accountAddReqBO.getAddress());
        accountInfoDO.setAccount(accountAddReqBO.getAccount());
        accountInfoDO.setPassword(accountAddReqBO.getPassword());
        accountInfoDO.setRemark(accountAddReqBO.getRemark());
        accountInfoDO.setCreatedBy(accountAddReqBO.getUserNo());
        accountInfoDO.setUpdatedBy(accountAddReqBO.getUserNo());

        return accountInfoDO;
    }

    /**
     * 根据请求BO获取DO
     * @param accountDeleteReqBO        BO
     * @return                          DO
     */
    public static AccountDeleteReqDO getDOByBO(AccountDeleteReqBO accountDeleteReqBO) {

        if (accountDeleteReqBO == null) {
            return null;
        }

        AccountDeleteReqDO accountDeleteReqDO = new AccountDeleteReqDO();
        accountDeleteReqDO.setUserNo(accountDeleteReqBO.getUserNo());
        accountDeleteReqDO.setId(accountDeleteReqBO.getId());

        return accountDeleteReqDO;
    }

    /**
     * 根据请求BO获取DO
     * @param accountQueryReqBO         BO
     * @return                          DO
     */
    public static AccountQueryReqDO getDOByBO(AccountQueryReqBO accountQueryReqBO) {

        if (accountQueryReqBO == null) {
            return null;
        }

        AccountQueryReqDO accountQueryReqDO = new AccountQueryReqDO();
        accountQueryReqDO.setUserNo(accountQueryReqBO.getUserNo());
        accountQueryReqDO.setAddress(accountQueryReqBO.getAddress());
        accountQueryReqDO.setAccount(accountQueryReqBO.getAccount());
        accountQueryReqDO.setCurrentPage(accountQueryReqBO.getCurrentPage());
        accountQueryReqDO.setPageSize(accountQueryReqBO.getPageSize());

        return accountQueryReqDO;
    }

    /**
     * 通过DO获取BO
     * @param accountInfoDO     DO
     * @return                  BO
     */
    public static AccountInfoBO getBOByDO(AccountInfoDO accountInfoDO) {

        if (accountInfoDO == null) {
            return null;
        }

        AccountInfoBO accountInfoBO = new AccountInfoBO();
        accountInfoBO.setUserNo(accountInfoDO.getUserNo());
        accountInfoBO.setAddress(accountInfoDO.getAddress());
        accountInfoBO.setAccount(accountInfoDO.getAccount());
        accountInfoBO.setPassword(accountInfoDO.getPassword());
        accountInfoBO.setStatus(accountInfoDO.getStatus());
        accountInfoBO.setRemark(accountInfoDO.getRemark());
        accountInfoBO.setId(accountInfoDO.getId());
        accountInfoBO.setCreatedAt(accountInfoDO.getCreatedAt());
        accountInfoBO.setCreatedBy(accountInfoDO.getCreatedBy());
        accountInfoBO.setUpdatedAt(accountInfoDO.getUpdatedAt());
        accountInfoBO.setUpdatedBy(accountInfoDO.getUpdatedBy());

        return accountInfoBO;
    }

    /**
     * DOs转BOs
     * @param accountInfoDOList     DOs
     * @return                      BOs
     */
    public static List<AccountInfoBO> getBOsByDOs(List<AccountInfoDO> accountInfoDOList) {

        if(CollectionUtils.isEmpty(accountInfoDOList)){
            return null;
        }

        List<AccountInfoBO> accountInfoBOList = new ArrayList<>();
        for(AccountInfoDO accountInfoDO : accountInfoDOList){
            accountInfoBOList.add(getBOByDO(accountInfoDO));
        }
        return accountInfoBOList;
    }
}
