package com.nicehancy.mix.service.convert.file;

import com.nicehancy.mix.common.enums.FileTypeEnum;
import com.nicehancy.mix.manager.model.FileUploadRecordBO;
import com.nicehancy.mix.manager.model.FileUploadRecordPageQueryReqBO;
import com.nicehancy.mix.manager.model.FileUploadRequestBO;
import com.nicehancy.mix.service.api.model.request.file.FileUploadRequestDTO;
import com.nicehancy.mix.service.api.model.request.note.FileUploadRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadRecordDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理DTO模型转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 13:55
 **/
public class FileManagementDTOConvert {

    /**
     * 私有构造函数
     */
    private FileManagementDTOConvert(){
    }

    /**
     * 文件上传请求DTO转BO
     * @param requestDTO            图片上传请求DTO
     * @return                      BO
     */
    public static FileUploadRequestBO getBOByDTO(FileUploadRequestDTO requestDTO) {

        if (requestDTO == null) {
            return null;
        }

        FileUploadRequestBO fileUploadRequestBO = new FileUploadRequestBO();
        fileUploadRequestBO.setUserNo(requestDTO.getUserNo());
        fileUploadRequestBO.setFileData(requestDTO.getFileData());

        return fileUploadRequestBO;
    }

    /**
     * 文件上传记录查询请求DTO转BO
     * @param requestDTO           上传请求DTO
     * @return                     BO
     */
    public static FileUploadRecordPageQueryReqBO getBOByDTO(FileUploadRecordPageQueryReqDTO requestDTO) {

        if (requestDTO == null) {
            return null;
        }

        FileUploadRecordPageQueryReqBO fileUploadRecordPageQueryReqBO = new FileUploadRecordPageQueryReqBO();
        fileUploadRecordPageQueryReqBO.setUserNo(requestDTO.getUserNo());
        fileUploadRecordPageQueryReqBO.setFileId(requestDTO.getFileId());
        fileUploadRecordPageQueryReqBO.setFileName(requestDTO.getFileName());
        fileUploadRecordPageQueryReqBO.setFileType(requestDTO.getFileType());
        fileUploadRecordPageQueryReqBO.setCurrentPage(requestDTO.getCurrentPage());
        fileUploadRecordPageQueryReqBO.setPageSize(requestDTO.getPageSize());

        return fileUploadRecordPageQueryReqBO;
    }

    /**
     * 根据BO获取DTO
     * @param fileUploadRecordBO    BO
     * @return                      DTO
     */
    public static FileUploadRecordDTO getDTOByBO(FileUploadRecordBO fileUploadRecordBO) {

        if (fileUploadRecordBO == null) {
            return null;
        }

        FileUploadRecordDTO fileUploadRecordDTO = new FileUploadRecordDTO();
        fileUploadRecordDTO.setUserNo(fileUploadRecordBO.getUserNo());
        fileUploadRecordDTO.setFileId(fileUploadRecordBO.getFileId());
        fileUploadRecordDTO.setFileName(fileUploadRecordBO.getFileName());
        fileUploadRecordDTO.setFileType(fileUploadRecordBO.getFileType());
        fileUploadRecordDTO.setFilePath(fileUploadRecordBO.getFilePath());
        fileUploadRecordDTO.setFileStatus(fileUploadRecordBO.getFileStatus());
        fileUploadRecordDTO.setCreatedAt(fileUploadRecordBO.getCreatedAt());
        fileUploadRecordDTO.setCreatedBy(fileUploadRecordBO.getCreatedBy());
        fileUploadRecordDTO.setUpdatedAt(fileUploadRecordBO.getUpdatedAt());
        fileUploadRecordDTO.setUpdatedBy(fileUploadRecordBO.getUpdatedBy());

        return fileUploadRecordDTO;
    }

    /**
     * 根据BOList获取DTOList
     * @param list                  bos
     * @return                      dtos
     */
    public static List<FileUploadRecordDTO> getDTOSByBOS(List<FileUploadRecordBO> list) {

        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        List<FileUploadRecordDTO> fileUploadRecordDTOS = new ArrayList<>();
        for(FileUploadRecordBO fileUploadRecordBO : list){
            FileUploadRecordDTO fileUploadRecordDTO = getDTOByBO(fileUploadRecordBO);
            fileUploadRecordDTO.setFileType(FileTypeEnum.expireOfCode(fileUploadRecordDTO.getFileType()).getDesc());
            fileUploadRecordDTOS.add(fileUploadRecordDTO);
        }
        return fileUploadRecordDTOS;
    }
}
