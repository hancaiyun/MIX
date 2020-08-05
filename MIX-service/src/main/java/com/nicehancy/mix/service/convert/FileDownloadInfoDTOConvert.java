package com.nicehancy.mix.service.convert;

import com.nicehancy.mix.manager.model.FileDownloadInfoBO;
import com.nicehancy.mix.manager.model.FileDownloadInfoPageQueryReqBO;
import com.nicehancy.mix.service.api.model.FileDownloadInfoDTO;
import com.nicehancy.mix.service.api.model.request.FileDownloadInfoPageQueryReqDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件下载DTO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/5 18:58
 **/
public class FileDownloadInfoDTOConvert {

    /**
     * 私有构造函数
     */
    private FileDownloadInfoDTOConvert(){
    }

    /**
     * 请求DTO转BO
     * @param reqDTO        DTO
     * @return              BO
     */
    public static FileDownloadInfoPageQueryReqBO getBOByDTO(FileDownloadInfoPageQueryReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        FileDownloadInfoPageQueryReqBO fileDownloadInfoPageQueryReqBO = new FileDownloadInfoPageQueryReqBO();
        fileDownloadInfoPageQueryReqBO.setUserNo(reqDTO.getUserNo());
        fileDownloadInfoPageQueryReqBO.setCurrentPage(reqDTO.getCurrentPage());
        fileDownloadInfoPageQueryReqBO.setPageSize(reqDTO.getPageSize());

        return fileDownloadInfoPageQueryReqBO;
    }

    /**
     * BO转DTO
     * @param fileDownloadInfoBO        BO
     * @return                          DTO
     */
    public static FileDownloadInfoDTO getDTOByBO(FileDownloadInfoBO fileDownloadInfoBO) {

        if (fileDownloadInfoBO == null) {
            return null;
        }

        FileDownloadInfoDTO fileDownloadInfoDTO = new FileDownloadInfoDTO();
        fileDownloadInfoDTO.setUserNo(fileDownloadInfoBO.getUserNo());
        fileDownloadInfoDTO.setFileId(fileDownloadInfoBO.getFileId());
        fileDownloadInfoDTO.setFileDesc(fileDownloadInfoBO.getFileDesc());
        fileDownloadInfoDTO.setCreateResult(fileDownloadInfoBO.getCreateResult());
        fileDownloadInfoDTO.setFullFilePath(fileDownloadInfoBO.getFullFilePath());
        fileDownloadInfoDTO.setCreatedAt(fileDownloadInfoBO.getCreatedAt());
        fileDownloadInfoDTO.setCreatedBy(fileDownloadInfoBO.getCreatedBy());
        fileDownloadInfoDTO.setUpdatedAt(fileDownloadInfoBO.getUpdatedAt());
        fileDownloadInfoDTO.setUpdatedBy(fileDownloadInfoBO.getUpdatedBy());

        return fileDownloadInfoDTO;
    }

    /**
     * BOs转DTOs
     * @param list          BOs
     * @return              DTOs
     */
    public static List<FileDownloadInfoDTO> getDTOsByBOs(List<FileDownloadInfoBO> list) {

        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        List<FileDownloadInfoDTO> fileDownloadInfoDTOS = new ArrayList<>();
        for(FileDownloadInfoBO fileDownloadInfoBO : list){
            fileDownloadInfoDTOS.add(getDTOByBO(fileDownloadInfoBO));
        }
        return fileDownloadInfoDTOS;
    }
}
