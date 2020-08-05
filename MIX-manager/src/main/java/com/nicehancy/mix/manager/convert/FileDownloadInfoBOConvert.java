package com.nicehancy.mix.manager.convert;

import com.nicehancy.mix.dal.model.FileDownloadInfoDO;
import com.nicehancy.mix.dal.model.request.FileDownloadInfoPageQueryReqDO;
import com.nicehancy.mix.manager.model.FileDownloadInfoBO;
import com.nicehancy.mix.manager.model.FileDownloadInfoPageQueryReqBO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件下载BO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/5 18:22
 **/
public class FileDownloadInfoBOConvert {

    /**
     * 私有构造
     */
    private FileDownloadInfoBOConvert(){
    }

    /**
     * 分页请求BO转DO
     * @param reqBO                 BO
     * @return                      DO
     */
    public static FileDownloadInfoPageQueryReqDO getDOByBO(FileDownloadInfoPageQueryReqBO reqBO) {

        if (reqBO == null) {
            return null;
        }

        FileDownloadInfoPageQueryReqDO fileDownloadInfoPageQueryReqDO = new FileDownloadInfoPageQueryReqDO();
        fileDownloadInfoPageQueryReqDO.setUserNo(reqBO.getUserNo());
        fileDownloadInfoPageQueryReqDO.setCurrentPage(reqBO.getCurrentPage());
        fileDownloadInfoPageQueryReqDO.setPageSize(reqBO.getPageSize());

        return fileDownloadInfoPageQueryReqDO;
    }

    /**
     * 文件下载DO转BO
     * @param fileDownloadInfoDO        DO
     * @return                          BO
     */
    public static FileDownloadInfoBO getBOByDO(FileDownloadInfoDO fileDownloadInfoDO) {

        if (fileDownloadInfoDO == null) {
            return null;
        }

        FileDownloadInfoBO fileDownloadInfoBO = new FileDownloadInfoBO();
        fileDownloadInfoBO.setUserNo(fileDownloadInfoDO.getUserNo());
        fileDownloadInfoBO.setFileId(fileDownloadInfoDO.getFileId());
        fileDownloadInfoBO.setFileDesc(fileDownloadInfoDO.getFileDesc());
        fileDownloadInfoBO.setCreateResult(fileDownloadInfoDO.getCreateResult());
        fileDownloadInfoBO.setFullFilePath(fileDownloadInfoDO.getFullFilePath());
        fileDownloadInfoBO.setCreatedAt(fileDownloadInfoDO.getCreatedAt());
        fileDownloadInfoBO.setCreatedBy(fileDownloadInfoDO.getCreatedBy());
        fileDownloadInfoBO.setUpdatedAt(fileDownloadInfoDO.getUpdatedAt());
        fileDownloadInfoBO.setUpdatedBy(fileDownloadInfoDO.getUpdatedBy());

        return fileDownloadInfoBO;
    }

    /**
     * 文件下载DO转BO
     * @param fileDownloadInfoDOS       DOs
     * @return                          BOs
     */
    public static List<FileDownloadInfoBO> getBOsByDOs(List<FileDownloadInfoDO> fileDownloadInfoDOS){

        if(CollectionUtils.isEmpty(fileDownloadInfoDOS)){
            return null;
        }

        List<FileDownloadInfoBO> fileDownloadInfoBOS = new ArrayList<>();
        for(FileDownloadInfoDO fileDownloadInfoDO : fileDownloadInfoDOS){
            fileDownloadInfoBOS.add(getBOByDO(fileDownloadInfoDO));
        }

        return fileDownloadInfoBOS;
    }
}
