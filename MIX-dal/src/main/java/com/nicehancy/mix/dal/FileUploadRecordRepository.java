package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.FileUploadRecordDO;
import com.nicehancy.mix.dal.model.FileUploadRecordPageQueryReqDO;

import java.util.List;

/**
 * 文件上传记录
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/16 11:05
 **/
public interface FileUploadRecordRepository {

    /**
     * 新增文件上传记录
     * @param fileUploadRecordDO    文件上传记录DO
     */
    void insert(FileUploadRecordDO fileUploadRecordDO);

    /**
     * 分页查询总条目数
     * @param pageQueryReqDO        分页请求参数
     * @return                      总条目数
     */
    int queryCount(FileUploadRecordPageQueryReqDO pageQueryReqDO);

    /**
     * 分页查询结果集
     * @param pageQueryReqDO        分页请求参数
     * @return                      结果集
     */
    List<FileUploadRecordDO> pageQuery(FileUploadRecordPageQueryReqDO pageQueryReqDO);
}
