package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.FileDownloadInfoRepository;
import com.nicehancy.mix.dal.model.FileDownloadInfoDO;
import com.nicehancy.mix.dal.model.request.FileDownloadInfoPageQueryReqDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 文件下载中心
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/4 14:33
 **/
@Repository(value = "fileDownloadInfoRepositoryImpl")
public class FileDownloadInfoRepositoryImpl implements FileDownloadInfoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 文件下载信息新增
     * @param fileDownloadInfoDO    文件下载信息DO
     */
    @Override
    public void insert(FileDownloadInfoDO fileDownloadInfoDO) {
        //字段初始化
        fileDownloadInfoDO.setFileId(UUIDUtil.createNoByUUId());
        fileDownloadInfoDO.setCreatedAt(new Date());
        fileDownloadInfoDO.setUpdatedAt(new Date());

        mongoTemplate.insert(fileDownloadInfoDO);
    }

    /**
     * 分页查询总条目数
     * @param pageQueryReqDO        分页请求参数
     * @return                      条目数
     */
    @Override
    public int queryCount(FileDownloadInfoPageQueryReqDO pageQueryReqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(pageQueryReqDO.getUserNo());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //查询
        long count = mongoTemplate.count(query, FileDownloadInfoDO.class);

        return (int) count;
    }

    /**
     * 分页查询结果集
     * @param pageQueryReqDO        分页请求参数
     * @return                      结果集
     */
    @Override
    public List<FileDownloadInfoDO> pageQuery(FileDownloadInfoPageQueryReqDO pageQueryReqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(pageQueryReqDO.getUserNo());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //分页
        int pageNumber = pageQueryReqDO.getCurrentPage();
        int pageSize = pageQueryReqDO.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, FileDownloadInfoDO.class);
    }

    /**
     * 下载信息明细查询
     * @param fileId                文件ID
     * @return                      下载信息明细
     */
    @Override
    public FileDownloadInfoDO queryDetail(String fileId) {

        //设置查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("fileId").is(fileId);
        query.addCriteria(criteria);

        return mongoTemplate.findOne(query, FileDownloadInfoDO.class);
    }
}
