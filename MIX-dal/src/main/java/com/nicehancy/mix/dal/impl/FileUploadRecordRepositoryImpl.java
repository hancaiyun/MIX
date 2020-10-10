package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.enums.FileStatusEnum;
import com.nicehancy.mix.common.enums.ShareFlagEnum;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.FileUploadRecordRepository;
import com.nicehancy.mix.dal.model.FileUploadRecordDO;
import com.nicehancy.mix.dal.model.request.FileUploadRecordPageQueryReqDO;
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

/**
 * 文件上传记录
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/16 11:07
 **/
@Repository(value = "fileUploadRecordRepositoryImpl")
public class FileUploadRecordRepositoryImpl implements FileUploadRecordRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增文件上传记录
     * @param fileUploadRecordDO    文件上传记录DO
     */
    @Override
    public void insert(FileUploadRecordDO fileUploadRecordDO) {

        //字段初始化
        fileUploadRecordDO.setFileId(UUIDUtil.createNoByUUId());
        fileUploadRecordDO.setFileStatus(FileStatusEnum.NORMAL.getCode());
        fileUploadRecordDO.setCreatedAt(new Date());
        fileUploadRecordDO.setUpdatedAt(new Date());

        mongoTemplate.insert(fileUploadRecordDO);
    }

    /**
     * 分页查询总条目数
     * @param pageQueryReqDO        分页请求参数
     * @return                      总条目数
     */
    @Override
    public int queryCount(FileUploadRecordPageQueryReqDO pageQueryReqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(pageQueryReqDO.getFileId())){
            criteria.and("fileId").is(pageQueryReqDO.getFileId());
        }
        if(!StringUtils.isEmpty(pageQueryReqDO.getFileName())){
            criteria.and("fileName").is(pageQueryReqDO.getFileName());
        }
        if(!StringUtils.isEmpty(pageQueryReqDO.getFileType())){
            criteria.and("fileType").is(pageQueryReqDO.getFileType());
        }
        criteria.and("userNo").is(pageQueryReqDO.getUserNo());
        criteria.and("fileStatus").ne(FileStatusEnum.DELETE.getCode());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //查询
        long count = mongoTemplate.count(query, FileUploadRecordDO.class);

        return (int) count;
    }

    /**
     * 分页查询结果集
     * @param pageQueryReqDO        分页请求参数
     * @return                      分页结果集
     */
    @Override
    public List<FileUploadRecordDO> pageQuery(FileUploadRecordPageQueryReqDO pageQueryReqDO) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(pageQueryReqDO.getFileId())){
            criteria.and("fileId").is(pageQueryReqDO.getFileId());
        }
        if(!StringUtils.isEmpty(pageQueryReqDO.getFileName())){
            criteria.and("fileName").is(pageQueryReqDO.getFileName());
        }
        if(!StringUtils.isEmpty(pageQueryReqDO.getFileType())){
            criteria.and("fileType").is(pageQueryReqDO.getFileType());
        }
        criteria.and("userNo").is(pageQueryReqDO.getUserNo());
        criteria.and("fileStatus").ne(FileStatusEnum.DELETE.getCode());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        //分页
        int pageNumber = pageQueryReqDO.getCurrentPage();
        int pageSize = pageQueryReqDO.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, FileUploadRecordDO.class);
    }

    /**
     * 文件上传记录明细查询
     * @param fileId                文件ID
     * @return                      文件上传明细
     */
    @Override
    public FileUploadRecordDO queryDetail(String fileId) {

        //设置查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("fileId").is(fileId);
        criteria.and("fileStatus").ne(FileStatusEnum.DELETE.getCode());
        query.addCriteria(criteria);

        return mongoTemplate.findOne(query, FileUploadRecordDO.class);
    }

    /**
     * 文件信息更新
     * @param fileUploadRecordDO    更新内容
     */
    @Override
    public void updateFileInfo(FileUploadRecordDO fileUploadRecordDO) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("fileId").is(fileUploadRecordDO.getFileId());
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        if(!StringUtils.isEmpty(fileUploadRecordDO.getFileStatus())){
            update.set("fileStatus", fileUploadRecordDO.getFileStatus());
        }

        update.set("updatedAt", new Date());
        update.set("updatedBy", fileUploadRecordDO.getUpdatedBy());

        //更新操作
        mongoTemplate.updateFirst(query, update, FileUploadRecordDO.class);
    }

    /**
     * 文件共享更新
     * @param fileId                文件ID
     * @param operator              操作人
     */
    @Override
    public void shareFile(String fileId, String operator) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("fileId").is(fileId);
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        update.set("shareFlag", ShareFlagEnum.TRUE.getCode());
        update.set("updatedAt", new Date());
        update.set("updatedBy", operator);

        //更新操作
        mongoTemplate.updateFirst(query, update, FileUploadRecordDO.class);
    }
}
