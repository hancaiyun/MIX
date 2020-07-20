package com.nicehancy.mix.service.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.note.AccountAddReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.AccountInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.note.AccountManagementService;

/**
 * 账号管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/20 18:15
 **/
public class AccountManagementServiceImpl implements AccountManagementService {

    /**
     * 账号新增
     * @param accountAddReqDTO      新增请求参数
     * @return                      新增结果
     */
    @Override
    public Result<Boolean> add(AccountAddReqDTO accountAddReqDTO) {
        return null;
    }

    /**
     * 账号查询
     * @param queryReqDTO           请求DTO
     * @return                      查询结果集
     */
    @Override
    public Result<BasePageQueryResDTO<AccountInfoDTO>> pageQuery(AccountQueryReqDTO queryReqDTO) {
        return null;
    }
}
