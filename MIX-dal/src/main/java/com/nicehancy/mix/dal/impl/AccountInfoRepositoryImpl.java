package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.dal.AccountInfoRepository;
import com.nicehancy.mix.dal.model.AccountInfoDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户信息管理
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 15:38
 **/
@Repository(value = "accountInfoRepositoryImpl")
public class AccountInfoRepositoryImpl implements AccountInfoRepository {
    @Override
    public int pageCount() {
        return 0;
    }

    @Override
    public List<AccountInfoDO> pageQuery() {
        return null;
    }
}
