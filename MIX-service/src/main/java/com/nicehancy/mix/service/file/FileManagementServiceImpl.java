package com.nicehancy.mix.service.file;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.file.document.FileManagementManager;
import com.nicehancy.mix.manager.model.FileUploadResultBO;
import com.nicehancy.mix.service.api.file.FileManagementService;
import com.nicehancy.mix.service.api.model.request.file.FileDownloadRequestDTO;
import com.nicehancy.mix.service.api.model.request.file.FileUploadRequestDTO;
import com.nicehancy.mix.service.api.model.result.FileDownloadResultDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadResultDTO;
import com.nicehancy.mix.service.convert.file.FileManagementDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 文件管理实现类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 11:13
 **/
@Slf4j
@Service
public class FileManagementServiceImpl implements FileManagementService {

    /**
     * 文件管理manager
     */
    @Autowired
    private FileManagementManager fileManagementManager;

    /**
     * 文件上传接口
     * @param requestDTO       请求对象
     * @return                 上传结果
     */
    @Override
    public Result<FileUploadResultDTO> uploadFile(FileUploadRequestDTO requestDTO) {

        Result<FileUploadResultDTO> result = new Result<>();
        FileUploadResultDTO resultDTO = new FileUploadResultDTO();
        try{
            MDC.put(CommonConstant.TRACE_LOG_ID, requestDTO.getTraceLogId());
            log.info("call FileManagementService uploadFile request:{}", requestDTO);
            //参数校验
            //VerifyUtil.validateObject(requestDTO);
            //业务处理
            FileUploadResultBO resultBO = fileManagementManager.uploadFile(FileManagementDTOConvert.getBOByDTO(requestDTO));
            resultDTO.setFileId(resultBO.getFileId());
            resultDTO.setFileName(resultBO.getFileName());
            result.setResult(resultDTO);
            //结果封装
            log.info("call FileManagementService uploadFile success, result:{}", result);
        }catch (Exception e){
            log.error("call FileManagementService uploadFile Fail, exception:{}, result:{}", e, result);
            //异常信息包装

            log.error("call FileManagementService Fail,result:{}", result);
        }finally {
            MDC.clear();
        }
        return result;
    }

    /**
     * 文件下载接口
     * @param requestDTO       请求对象
     * @return                 返回结果
     */
    @Override
    public Result<FileDownloadResultDTO> downloadFile(FileDownloadRequestDTO requestDTO) {

        Result<FileDownloadResultDTO> result = new Result<>();
        try{
            MDC.put(CommonConstant.TRACE_LOG_ID, requestDTO.getTraceLogId());
            log.info("call FileManagementService downloadFile request:{}", requestDTO);

            //参数校验
            VerifyUtil.validateObject(requestDTO);

            //业务处理

            //结果封装

            log.info("call FileManagementService downloadFile success, result:{}", result);
        }catch (Exception e){
            log.error("call FileManagementService downloadFile Fail, exception:{}, result:{}", e, result);
            //异常信息包装

            log.error("call FileManagementService downloadFile Fail,result:{}", result);
        }finally {
            MDC.clear();
        }
        return result;
    }
}
