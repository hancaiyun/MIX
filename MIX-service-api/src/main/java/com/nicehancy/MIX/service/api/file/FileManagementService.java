package com.nicehancy.MIX.service.api.file;

import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.service.api.model.request.file.FileDownloadRequestDTO;
import com.nicehancy.MIX.service.api.model.request.file.FileUploadRequestDTO;
import com.nicehancy.MIX.service.api.model.result.FileDownloadResultDTO;
import com.nicehancy.MIX.service.api.model.result.FileUploadResultDTO;

/**
 * 文件管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 11:13
 **/
public interface FileManagementService {

    /**
     * 文件上传
     * @param requestDTO       请求对象
     * @return                 图片路径
     */
    Result<FileUploadResultDTO> uploadFile(FileUploadRequestDTO requestDTO);

    /**
     * 文件下载
     * @param requestDTO       请求对象
     * @return                 下载结果
     */
    Result<FileDownloadResultDTO> downloadFile(FileDownloadRequestDTO requestDTO);

}
