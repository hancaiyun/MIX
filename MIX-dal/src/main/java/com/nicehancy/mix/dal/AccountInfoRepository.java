package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.AccountInfoDO;
import com.nicehancy.mix.dal.model.request.AccountDeleteReqDO;
import com.nicehancy.mix.dal.model.request.AccountQueryReqDO;

import java.util.List;

/**
 * 账户信息repository
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 15:35
 **/
public interface AccountInfoRepository {

    /**
     * 新增
     * @param accountInfoDO     账户信息DO
     */
    void add(AccountInfoDO accountInfoDO);

    /**
     * 查询分页总条目数
     * @param accountQueryReqDO 分页请求参数
     * @return                  总条目数
     */
    int queryCount(AccountQueryReqDO accountQueryReqDO);

    /**
     * 分页查询结果集
     * @param accountQueryReqDO 请求参数
     * @return                  结果集
     */
    List<AccountInfoDO> pageQuery(AccountQueryReqDO accountQueryReqDO);

    /**
     * 账户删除
     * @param accountDeleteReqDO 请求参数DO
     */
    void delete(AccountDeleteReqDO accountDeleteReqDO);
}
