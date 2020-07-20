package com.nicehancy.mix.service.api.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.note.AccountAddReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.AccountInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;

/**
 * 账号管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/20 18:12
 **/
public interface AccountManagementService {

    /**
     * 账号新增接口
     * @param accountAddReqDTO      新增请求参数
     * @return                      新增结果
     */
    Result<Boolean> add(AccountAddReqDTO accountAddReqDTO);

    /**
     * 账号分页查询接口
     * @param queryReqDTO           请求DTO
     * @return                      分页结果
     */
    Result<BasePageQueryResDTO<AccountInfoDTO>> pageQuery(AccountQueryReqDTO queryReqDTO);
}
