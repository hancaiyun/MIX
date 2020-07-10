package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.AccountInfoDO;
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

    int pageCount();

    List<AccountInfoDO> pageQuery();
}
