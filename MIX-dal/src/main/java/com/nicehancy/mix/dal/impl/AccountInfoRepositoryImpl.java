package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.enums.AccountStatusEnum;
import com.nicehancy.mix.common.enums.FileStatusEnum;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.AccountInfoRepository;
import com.nicehancy.mix.dal.model.AccountInfoDO;
import com.nicehancy.mix.dal.model.FileUploadRecordDO;
import com.nicehancy.mix.dal.model.request.AccountDeleteReqDO;
import com.nicehancy.mix.dal.model.request.AccountQueryReqDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 账户新增
     * @param accountInfoDO    账户信息DO
     */
    @Override
    public void add(AccountInfoDO accountInfoDO) {
        //字段初始化
        accountInfoDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        accountInfoDO.setStatus(AccountStatusEnum.NORMAL.getCode());
        accountInfoDO.setCreatedAt(new Date());
        accountInfoDO.setUpdatedAt(new Date());

        mongoTemplate.insert(accountInfoDO);
    }

    /**
     * 分页查询总条目数
     * @param accountQueryReqDO 分页请求参数
     * @return                  总条目数
     */
    @Override
    public int queryCount(AccountQueryReqDO accountQueryReqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(accountQueryReqDO.getAddress())){
            Pattern pattern=Pattern.compile("^.*"+accountQueryReqDO.getAddress()+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("address").regex(pattern);
        }
        if(!StringUtils.isEmpty(accountQueryReqDO.getAccount())){
            Pattern pattern=Pattern.compile("^.*"+accountQueryReqDO.getAccount()+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("account").regex(pattern);
        }
        criteria.and("userNo").is(accountQueryReqDO.getUserNo());
        criteria.and("status").ne(FileStatusEnum.DELETE.getCode());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //查询
        long count = mongoTemplate.count(query, AccountInfoDO.class);

        return (int) count;
    }

    /**
     * 分页查询结果集
     * @param accountQueryReqDO 请求参数
     * @return                  分页结果集
     */
    @Override
    public List<AccountInfoDO> pageQuery(AccountQueryReqDO accountQueryReqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(accountQueryReqDO.getAddress())){
            Pattern pattern=Pattern.compile("^.*"+accountQueryReqDO.getAddress()+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("address").regex(pattern);
        }
        if(!StringUtils.isEmpty(accountQueryReqDO.getAccount())){
            Pattern pattern=Pattern.compile("^.*"+accountQueryReqDO.getAccount()+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("account").regex(pattern);
        }
        criteria.and("userNo").is(accountQueryReqDO.getUserNo());
        criteria.and("status").ne(FileStatusEnum.DELETE.getCode());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //分页
        int pageNumber = accountQueryReqDO.getCurrentPage();
        int pageSize = accountQueryReqDO.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, AccountInfoDO.class);
    }

    /**
     * 删除（状态更新为不可用）
     * @param accountDeleteReqDO 请求参数DO
     */
    @Override
    public void delete(AccountDeleteReqDO accountDeleteReqDO) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("id").is(accountDeleteReqDO.getId());
        criteria.and("userNo").is(accountDeleteReqDO.getUserNo());
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        update.set("status", AccountStatusEnum.DELETE.getCode());
        update.set("updatedAt", new Date());
        update.set("updatedBy", accountDeleteReqDO.getUserNo());

        //更新操作
        mongoTemplate.updateFirst(query, update, AccountInfoDO.class);
    }
}
