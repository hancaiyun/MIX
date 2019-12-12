package com.nicehancy.MIX.service.file;

import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.common.constant.CommonConstant;
import com.nicehancy.MIX.common.utils.VerifyUtil;
import com.nicehancy.MIX.manager.file.document.FileManagementManager;
import com.nicehancy.MIX.manager.model.FileUploadResultBO;
import com.nicehancy.MIX.service.api.file.FileManagementService;
import com.nicehancy.MIX.service.api.model.request.FileDownloadRequestDTO;
import com.nicehancy.MIX.service.api.model.request.FileUploadRequestDTO;
import com.nicehancy.MIX.service.api.model.result.FileDownloadResultDTO;
import com.nicehancy.MIX.service.api.model.result.FileUploadResultDTO;
import com.nicehancy.MIX.service.convert.file.FileManagementDTOConvert;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件管理实现类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 11:13
 **/
@Slf4j
@Service(timeout = 3000)
@Component("fileManagementServiceImpl")
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
