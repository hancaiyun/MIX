package com.nicehancy.mix.service.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.model.AccountInfoBO;
import com.nicehancy.mix.manager.note.AccountManager;
import com.nicehancy.mix.service.api.model.request.note.AccountAddReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountDeleteReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.AccountInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.note.AccountManagementService;
import com.nicehancy.mix.service.convert.note.AccountInfoDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 账号管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/20 18:15
 **/
@Slf4j
@Service
public class AccountManagementServiceImpl implements AccountManagementService {

    @Autowired
    private AccountManager accountManager;

    /**
     * 账号新增
     * @param accountAddReqDTO      新增请求参数
     * @return                      新增结果
     */
    @Override
    public Result<Boolean> add(AccountAddReqDTO accountAddReqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", accountAddReqDTO.getTraceLogId());
        try{
            log.info("call AccountManagementServiceImpl add param: reqDTO={}", accountAddReqDTO);
            //参数校验
            VerifyUtil.validateObject(accountAddReqDTO);
            //业务处理
            accountManager.add(AccountInfoDTOConvert.getReqBOByDTO(accountAddReqDTO));
            result.setResult(true);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call AccountManagementServiceImpl add failed, message：e={}， result={}", e, result);
        }
        log.info("call AccountManagementServiceImpl add result: {}", result);
        return result;
    }

    /**
     * 账号查询
     * @param queryReqDTO           请求DTO
     * @return                      查询结果集
     */
    @Override
    public Result<BasePageQueryResDTO<AccountInfoDTO>> pageQuery(AccountQueryReqDTO queryReqDTO) {

        Result<BasePageQueryResDTO<AccountInfoDTO>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", queryReqDTO.getTraceLogId());
        try{
            log.info("call AccountManagementServiceImpl pageQuery param: reqDTO={}", queryReqDTO);

            //参数校验
            VerifyUtil.validateObject(queryReqDTO);

            //业务处理
            BasePageQueryResDTO<AccountInfoDTO> resDTO= new BasePageQueryResDTO<>();

            //查询总条数
            int count = accountManager.queryCount(AccountInfoDTOConvert.getBOByDTO(queryReqDTO));

            //查询结果集
            if(0 == count) {
                resDTO.setPageResult(null);
                resDTO.setCount(0);
            }else{
                List<AccountInfoBO> list = accountManager.pageQuery(AccountInfoDTOConvert.getBOByDTO(queryReqDTO));
                List<AccountInfoDTO> dtoList = AccountInfoDTOConvert.getDTOsByBOs(list);
                resDTO.setPageResult(dtoList);
                resDTO.setCount(count);
            }
            result.setResult(resDTO);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call AccountManagementServiceImpl pageQuery failed, message：e={}， result={}", e, result);
        }
        log.info("call AccountManagementServiceImpl pageQuery result: {}", result);
        return result;
    }

    /**
     * 账户删除请求DTO
     * @param reqDTO                请求DTO
     * @return                      删除结果
     */
    @Override
    public Result<Boolean> delete(AccountDeleteReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call AccountManagementServiceImpl delete param: reqDTO={}", reqDTO);
            //参数校验
            VerifyUtil.validateObject(reqDTO);

            //业务处理
            accountManager.delete(AccountInfoDTOConvert.getReqBOByDTO(reqDTO));
            result.setResult(true);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call AccountManagementServiceImpl delete failed, message：e={}， result={}", e, result);
        }
        log.info("call AccountManagementServiceImpl delete result: {}", result);
        return result;
    }
}
