package com.nicehancy.MIX.service.convert.file;

import com.nicehancy.MIX.manager.model.FileUploadRequestBO;
import com.nicehancy.MIX.service.api.model.request.FileUploadRequestDTO;

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
        fileUploadRequestBO.setFileType(requestDTO.getFileType());
        fileUploadRequestBO.setSubFileType(requestDTO.getSubFileType());
        fileUploadRequestBO.setFileData(requestDTO.getFileData());

        return fileUploadRequestBO;
    }
}
