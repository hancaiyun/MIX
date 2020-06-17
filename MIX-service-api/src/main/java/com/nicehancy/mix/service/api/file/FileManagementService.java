package com.nicehancy.mix.service.api.file;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.file.FileDownloadRequestDTO;
import com.nicehancy.mix.service.api.model.request.file.FileQueryDetailReqDTO;
import com.nicehancy.mix.service.api.model.request.file.FileUploadRequestDTO;
import com.nicehancy.mix.service.api.model.request.note.FileUploadRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.FileDownloadResultDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadRecordDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadResultDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;

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
     * 文件上传记录分页查询
     * @param reqDTO           请求对象
     * @return                 分页结果
     */
    Result<BasePageQueryResDTO<FileUploadRecordDTO>> pageQuery(FileUploadRecordPageQueryReqDTO reqDTO);

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

    /**
     * 文件明细查询
     * @param requestDTO       请求对象
     * @return                 查询结果
     */
    Result<FileUploadRecordDTO> queryDetail(FileQueryDetailReqDTO requestDTO);

}
