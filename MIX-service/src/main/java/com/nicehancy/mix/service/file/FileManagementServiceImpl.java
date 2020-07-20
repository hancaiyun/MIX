package com.nicehancy.mix.service.file;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.enums.FileTypeEnum;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.file.document.FileManagementManager;
import com.nicehancy.mix.manager.model.FileUploadRecordBO;
import com.nicehancy.mix.manager.model.FileUploadResultBO;
import com.nicehancy.mix.service.api.file.FileManagementService;
import com.nicehancy.mix.service.api.model.request.file.FileDeleteRequestDTO;
import com.nicehancy.mix.service.api.model.request.file.FileQueryDetailReqDTO;
import com.nicehancy.mix.service.api.model.request.file.FileUploadRequestDTO;
import com.nicehancy.mix.service.api.model.request.note.FileUploadRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.FileDownloadResultDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadRecordDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadResultDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.convert.file.FileManagementDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 文件上传记录分页查询接口
     * @param reqDTO           请求对象
     * @return                 分页结果
     */
    @Override
    public Result<BasePageQueryResDTO<FileUploadRecordDTO>> pageQuery(FileUploadRecordPageQueryReqDTO reqDTO) {

        Result<BasePageQueryResDTO<FileUploadRecordDTO>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call FileManagementServiceImpl pageQuery param: reqDTO={}", reqDTO);
            //参数校验


            //业务处理
            BasePageQueryResDTO<FileUploadRecordDTO> resDTO = new BasePageQueryResDTO<>();

            //查询总条数
            int count = fileManagementManager.queryCount(FileManagementDTOConvert.getBOByDTO(reqDTO));

            //查询结果集
            if(0 == count) {
                resDTO.setPageResult(null);
                resDTO.setCount(0);
            }else{
                List<FileUploadRecordBO> list = fileManagementManager.pageQuery(FileManagementDTOConvert.getBOByDTO(reqDTO));
                List<FileUploadRecordDTO> dtoList = FileManagementDTOConvert.getDTOSByBOS(list);
                resDTO.setPageResult(dtoList);
                resDTO.setCount(count);
            }
            result.setResult(resDTO);
            log.info("call FileManagementServiceImpl pageQuery result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call FileManagementServiceImpl pageQuery failed, message：e={}， result={}", e, result);
        }
        return result;
    }

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
            VerifyUtil.validateObject(requestDTO);
            //业务处理
            FileUploadResultBO resultBO = fileManagementManager.uploadFile(FileManagementDTOConvert.getBOByDTO(requestDTO));
            resultDTO.setFileType(FileTypeEnum.expireOfCode(resultBO.getFileType()).getDesc());
            resultDTO.setFileName(resultBO.getFileName());
            result.setResult(resultDTO);
            //结果封装
            log.info("call FileManagementService uploadFile success, result:{}", result);
        }catch (Exception e){
            log.error("call FileManagementService uploadFile Fail, exception:{}, result:{}", e, result);
            //异常信息包装
            result.setErrorMsg(e.getMessage());
            log.error("call FileManagementService uploadFile Fail,result:{}", result);
        }
        return result;
    }

    @Override
    public Result<FileUploadRecordDTO> queryDetail(FileQueryDetailReqDTO requestDTO) {

        Result<FileUploadRecordDTO> result = new Result<>();
        try{
            MDC.put(CommonConstant.TRACE_LOG_ID, requestDTO.getTraceLogId());
            log.info("call FileManagementService queryDetail request:{}", requestDTO);

            //参数校验
            VerifyUtil.validateObject(requestDTO);

            //业务处理
            FileUploadRecordBO fileUploadRecordBO = fileManagementManager.queryDetail(requestDTO.getFileId());

            //结果封装
            result.setResult(FileManagementDTOConvert.getDTOByBO(fileUploadRecordBO));

            log.info("call FileManagementService queryDetail success, result:{}", result);
        }catch (Exception e){
            log.error("call FileManagementService queryDetail Fail, exception:{}, result:{}", e, result);
            //异常信息包装
            result.setErrorMsg(e.getMessage());
            log.error("call FileManagementService queryDetail Fail,result:{}", result);
        }
        return result;
    }

    /**
     * 文件删除接口
     * @param requestDTO       请求对象
     * @return                 返回结果
     */
    @Override
    public Result<Boolean> deleteFile(FileDeleteRequestDTO requestDTO) {

        Result<Boolean> result = new Result<>();
        try{
            MDC.put(CommonConstant.TRACE_LOG_ID, requestDTO.getTraceLogId());
            log.info("call FileManagementService deleteFile request:{}", requestDTO);

            //参数校验
            VerifyUtil.validateObject(requestDTO);

            //业务处理
            boolean isDelete = fileManagementManager.deleteFile(requestDTO.getFileId(), requestDTO.getOperator());
            //结果封装
            result.setResult(isDelete);
            log.info("call FileManagementService deleteFile success, result:{}", result);
        }catch (Exception e){
            log.error("call FileManagementService deleteFile Fail, exception:{}, result:{}", e, result);
            //异常信息包装
            result.setErrorMsg(e.getMessage());
            log.error("call FileManagementService deleteFile Fail,result:{}", result);
        }
        return result;
    }
}
