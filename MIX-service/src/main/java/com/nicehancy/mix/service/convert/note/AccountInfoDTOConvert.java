package com.nicehancy.mix.service.convert.note;

import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.manager.model.*;
import com.nicehancy.mix.service.api.model.request.note.AccountAddReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountDeleteReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountImportReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.AccountInfoDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号管理DTO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/21 12:11
 **/
public class AccountInfoDTOConvert {

    /**
     * 私有构造函数
     */
    private AccountInfoDTOConvert(){
    }

    /**
     * 获取BO
     * @param accountAddReqDTO      DTO
     * @return                      BO
     */
    public static AccountAddReqBO getReqBOByDTO(AccountAddReqDTO accountAddReqDTO) {

        if (accountAddReqDTO == null) {
            return null;
        }

        AccountAddReqBO accountAddReqBO = new AccountAddReqBO();
        accountAddReqBO.setUserNo(accountAddReqDTO.getUserNo());
        accountAddReqBO.setAddress(accountAddReqDTO.getAddress());
        accountAddReqBO.setAccount(accountAddReqDTO.getAccount());
        accountAddReqBO.setAccountType(accountAddReqDTO.getAccountType());
        accountAddReqBO.setPassword(accountAddReqDTO.getPassword());
        accountAddReqBO.setRemark(accountAddReqDTO.getRemark());

        return accountAddReqBO;
    }

    /**
     * 获取BO
     * @param accountAddReqDTO      DTO
     * @return                      BO
     */
    public static AccountImportReqBO getReqBOByDTO(AccountImportReqDTO accountAddReqDTO) {

        if (accountAddReqDTO == null) {
            return null;
        }

        AccountImportReqBO accountImportReqBO = new AccountImportReqBO();
        accountImportReqBO.setUserNo(accountAddReqDTO.getUserNo());
        accountImportReqBO.setFileData(accountAddReqDTO.getFileData());
        accountImportReqBO.setTraceLogId(accountAddReqDTO.getTraceLogId());

        return accountImportReqBO;
    }

    /**
     * 获取BO
     * @param accountDeleteReqDTO       DTO
     * @return                          BO
     */
    public static AccountDeleteReqBO getReqBOByDTO(AccountDeleteReqDTO accountDeleteReqDTO) {

        if (accountDeleteReqDTO == null) {
            return null;
        }

        AccountDeleteReqBO accountDeleteReqBO = new AccountDeleteReqBO();
        accountDeleteReqBO.setUserNo(accountDeleteReqDTO.getUserNo());
        accountDeleteReqBO.setId(accountDeleteReqDTO.getId());

        return accountDeleteReqBO;
    }

    /**
     * 分页
     * @param queryReqDTO           请求DTO
     * @return                      BO
     */
    public static AccountQueryReqBO getBOByDTO(AccountQueryReqDTO queryReqDTO) {

        if (queryReqDTO == null) {
            return null;
        }

        AccountQueryReqBO accountQueryReqBO = new AccountQueryReqBO();
        accountQueryReqBO.setUserNo(queryReqDTO.getUserNo());
        accountQueryReqBO.setAddress(queryReqDTO.getAddress());
        accountQueryReqBO.setAccount(queryReqDTO.getAccount());
        accountQueryReqBO.setAccountType(queryReqDTO.getAccountType());
        accountQueryReqBO.setCurrentPage(queryReqDTO.getCurrentPage());
        accountQueryReqBO.setPageSize(queryReqDTO.getPageSize());

        return accountQueryReqBO;
    }

    /**
     * BOS转DTOS
     * @param list                     BOS
     * @return                         DTOS
     */
    public static List<AccountInfoDTO> getDTOsByBOs(List<AccountInfoBO> list) {

        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        List<AccountInfoDTO> accountInfoDTOS = new ArrayList<>();
        for(AccountInfoBO accountInfoBO : list){
            accountInfoDTOS.add(getDTOByBO(accountInfoBO));
        }
        return accountInfoDTOS;
    }

    /**
     * BO 转DTO
     * @param accountInfoBO                BO
     * @return                             DTO
     */
    public static AccountInfoDTO getDTOByBO(AccountInfoBO accountInfoBO) {

        if (accountInfoBO == null) {
            return null;
        }

        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.setId(accountInfoBO.getId());
        accountInfoDTO.setUserNo(accountInfoBO.getUserNo());
        accountInfoDTO.setAddress(accountInfoBO.getAddress());
        accountInfoDTO.setAccount(accountInfoBO.getAccount());
        accountInfoDTO.setAccountType(accountInfoBO.getAccountType());
        accountInfoDTO.setPassword(accountInfoBO.getPassword());
        accountInfoDTO.setRemark(accountInfoBO.getRemark());
        accountInfoDTO.setUpdatedAt(DateUtil.format(accountInfoBO.getUpdatedAt(), DatePatternConstant.STANDARD_PATTERN));

        return accountInfoDTO;
    }
}
