package com.nicehancy.mix.manager.convert;

import com.nicehancy.mix.dal.model.FileUploadRecordDO;
import com.nicehancy.mix.dal.model.FileUploadRecordPageQueryReqDO;
import com.nicehancy.mix.manager.model.FileUploadRecordBO;
import com.nicehancy.mix.manager.model.FileUploadRecordPageQueryReqBO;
import com.nicehancy.mix.manager.model.FileUploadRequestBO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传记录BO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/16 12:28
 **/
public class FileRecordBOConvert {

    /**
     * 私有构造器
     */
    private FileRecordBOConvert(){
    }

    /**
     * 文件上传记录查询请求BO转DO
     * @param reqBO             请求BO
     * @return                  请求DO
     */
    public static FileUploadRecordPageQueryReqDO getDOByBO(FileUploadRecordPageQueryReqBO reqBO) {

        if (reqBO == null) {
            return null;
        }

        FileUploadRecordPageQueryReqDO fileUploadRecordPageQueryReqDO = new FileUploadRecordPageQueryReqDO();
        fileUploadRecordPageQueryReqDO.setUserNo(reqBO.getUserNo());
        fileUploadRecordPageQueryReqDO.setFileId(reqBO.getFileId());
        fileUploadRecordPageQueryReqDO.setFileName(reqBO.getFileName());
        fileUploadRecordPageQueryReqDO.setFileType(reqBO.getFileType());
        fileUploadRecordPageQueryReqDO.setCurrentPage(reqBO.getCurrentPage());
        fileUploadRecordPageQueryReqDO.setPageSize(reqBO.getPageSize());

        return fileUploadRecordPageQueryReqDO;
    }

    /**
     * 文件上传记录新增请求BO转DO
     * @param reqBO             请求BO
     * @return                  请求DO
     */
    public static FileUploadRecordDO getDOByBO(FileUploadRequestBO reqBO) {
        if (reqBO == null) {
            return null;
        }
        FileUploadRecordDO fileUploadRecordDO = new FileUploadRecordDO();
        fileUploadRecordDO.setUserNo(reqBO.getUserNo());
        fileUploadRecordDO.setFileName(reqBO.getFileName());
        fileUploadRecordDO.setFileType(reqBO.getFileType());
        fileUploadRecordDO.setFilePath(reqBO.getFilePath());
        fileUploadRecordDO.setCreatedBy(reqBO.getUserNo());
        fileUploadRecordDO.setUpdatedBy(reqBO.getUserNo());
        return fileUploadRecordDO;
    }

    /**
     * 根据DO获取BO
     * @param fileUploadRecordDO    DO
     * @return                      BO
     */
    public static FileUploadRecordBO getBOByDO(FileUploadRecordDO fileUploadRecordDO) {
        if (fileUploadRecordDO == null) {
            return null;
        }
        FileUploadRecordBO fileUploadRecordBO = new FileUploadRecordBO();
        fileUploadRecordBO.setUserNo(fileUploadRecordDO.getUserNo());
        fileUploadRecordBO.setFileId(fileUploadRecordDO.getFileId());
        fileUploadRecordBO.setFileName(fileUploadRecordDO.getFileName());
        fileUploadRecordBO.setFileType(fileUploadRecordDO.getFileType());
        fileUploadRecordBO.setFilePath(fileUploadRecordDO.getFilePath());
        fileUploadRecordBO.setFileStatus(fileUploadRecordDO.getFileStatus());
        fileUploadRecordBO.setCreatedAt(fileUploadRecordDO.getCreatedAt());
        fileUploadRecordBO.setCreatedBy(fileUploadRecordDO.getCreatedBy());
        fileUploadRecordBO.setUpdatedAt(fileUploadRecordDO.getUpdatedAt());
        fileUploadRecordBO.setUpdatedBy(fileUploadRecordDO.getUpdatedBy());
        return fileUploadRecordBO;
    }

    /**
     * 根据DOS获取BOS
     * @param fileUploadRecordDOList    DOs
     * @return                          BOs
     */
    public static List<FileUploadRecordBO> getBOSByDOS(List<FileUploadRecordDO> fileUploadRecordDOList){

        if(CollectionUtils.isEmpty(fileUploadRecordDOList)){
            return null;
        }

        List<FileUploadRecordBO> fileUploadRecordBOList = new ArrayList<>();
        for(FileUploadRecordDO fileUploadRecordDO : fileUploadRecordDOList){
            fileUploadRecordBOList.add(getBOByDO(fileUploadRecordDO));
        }
        return fileUploadRecordBOList;
    }
}
