package com.nicehancy.mix.manager.note;

import com.nicehancy.mix.dal.AccountInfoRepository;
import com.nicehancy.mix.dal.model.AccountInfoDO;
import com.nicehancy.mix.manager.convert.AccountInfoBOConvert;
import com.nicehancy.mix.manager.model.AccountAddReqBO;
import com.nicehancy.mix.manager.model.AccountDeleteReqBO;
import com.nicehancy.mix.manager.model.AccountInfoBO;
import com.nicehancy.mix.manager.model.AccountQueryReqBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 账户管理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 20:02
 **/
@Component
public class AccountManager {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    /**
     * 账户新增
     * @param accountAddReqBO   BO
     */
    public void add(AccountAddReqBO accountAddReqBO) {
        accountInfoRepository.add(AccountInfoBOConvert.getDOByBO(accountAddReqBO));
    }

    /**
     * 查询分页总条目数
     * @param reqBO         请求参数
     * @return              总条目数
     */
    public int queryCount(AccountQueryReqBO reqBO) {
        return accountInfoRepository.queryCount(AccountInfoBOConvert.getDOByBO(reqBO));
    }

    /**
     * 查询分页结果集
     * @param reqBO         请求参数
     * @return              总条目数
     */
    public List<AccountInfoBO> pageQuery(AccountQueryReqBO reqBO) {

        List<AccountInfoDO> accountInfoDOList = accountInfoRepository.pageQuery(AccountInfoBOConvert.getDOByBO(reqBO));
        return AccountInfoBOConvert.getBOsByDOs(accountInfoDOList);
    }

    /**
     * 账户删除
     * @param accountDeleteReqBO    请求BO
     */
    public void delete(AccountDeleteReqBO accountDeleteReqBO) {
        accountInfoRepository.delete(AccountInfoBOConvert.getDOByBO(accountDeleteReqBO));
    }
}
