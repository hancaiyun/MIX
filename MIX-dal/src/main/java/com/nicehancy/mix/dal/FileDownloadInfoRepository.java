package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.FileDownloadInfoDO;
import com.nicehancy.mix.dal.model.request.FileDownloadInfoPageQueryReqDO;

import java.util.List;

/**
 * 文件下载信息repository
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/4 14:25
 **/
public interface FileDownloadInfoRepository {

    /**
     * 新增文件下载信息
     * @param fileDownloadInfoDO    文件下载信息DO
     */
    void insert(FileDownloadInfoDO fileDownloadInfoDO);

    /**
     * 分页查询总条目数
     * @param pageQueryReqDO        分页请求参数
     * @return                      总条目数
     */
    int queryCount(FileDownloadInfoPageQueryReqDO pageQueryReqDO);

    /**
     * 分页查询结果集
     * @param pageQueryReqDO        分页请求参数
     * @return                      结果集
     */
    List<FileDownloadInfoDO> pageQuery(FileDownloadInfoPageQueryReqDO pageQueryReqDO);

    /**
     * 文件下载明细查询
     * @param fileId                文件ID
     * @return                      文件明细
     */
    FileDownloadInfoDO queryDetail(String fileId);
}
