package com.nicehancy.mix.service;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.FileDownloadInfoManager;
import com.nicehancy.mix.manager.model.FileDownloadInfoBO;
import com.nicehancy.mix.service.api.FileDownloadInfoService;
import com.nicehancy.mix.service.api.model.FileDownloadInfoDTO;
import com.nicehancy.mix.service.api.model.request.FileDownloadInfoPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.convert.FileDownloadInfoDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件下载中心接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/5 18:44
 **/
@Slf4j
@Service
public class FileDownloadInfoServiceImpl implements FileDownloadInfoService {

    @Autowired
    private FileDownloadInfoManager fileDownloadInfoManager;

    /**
     * 分页查询接口
     * @param reqDTO            请求DTO
     * @return                  分页结果集
     */
    @Override
    public Result<BasePageQueryResDTO<FileDownloadInfoDTO>> pageQuery(FileDownloadInfoPageQueryReqDTO reqDTO) {

        Result<BasePageQueryResDTO<FileDownloadInfoDTO>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call FileDownloadInfoServiceImpl pageQuery param: reqDTO={}", reqDTO);

            //参数校验
            VerifyUtil.validateObject(reqDTO);

            //业务处理
            BasePageQueryResDTO<FileDownloadInfoDTO> resDTO= new BasePageQueryResDTO<>();

            //查询总条数
            int count = fileDownloadInfoManager.queryCount(FileDownloadInfoDTOConvert.getBOByDTO(reqDTO));

            //查询结果集
            if(0 == count) {
                resDTO.setPageResult(null);
                resDTO.setCount(0);
            }else{
                List<FileDownloadInfoBO> list = fileDownloadInfoManager.pageQuery(FileDownloadInfoDTOConvert.getBOByDTO(
                        reqDTO));
                List<FileDownloadInfoDTO> dtoList = FileDownloadInfoDTOConvert.getDTOsByBOs(list);
                resDTO.setPageResult(dtoList);
                resDTO.setCount(count);
            }
            result.setResult(resDTO);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call FileDownloadInfoServiceImpl pageQuery failed, message：e={}， result={}", e, result);
        }
        log.info("call FileDownloadInfoServiceImpl pageQuery result: {}", result);

        return result;
    }
}
