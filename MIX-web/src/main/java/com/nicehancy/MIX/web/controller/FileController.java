package com.nicehancy.MIX.web.controller;

import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.service.api.file.FileManagementService;
import com.nicehancy.MIX.service.api.model.request.file.FileUploadRequestDTO;
import com.nicehancy.MIX.service.api.model.result.FileUploadResultDTO;
import com.nicehancy.MIX.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 文件服务controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 14:39
 **/
@Slf4j
@Controller
public class FileController extends BaseController {

    @Autowired
    private FileManagementService fileManagementService;

    /**
     * 文件上传
     * @return     文件路径
     */
    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public ModelMap upload(HttpServletRequest request){

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("files");
        log.info("文件:{}", files);

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileUploadRequestDTO requestDTO = new FileUploadRequestDTO();
        requestDTO.setTraceLogId(traceLogId);
        requestDTO.setRequestSystem("MIX");
        requestDTO.setUserNo(this.getParameters(request).get("userNo"));
        requestDTO.setFileType(this.getParameters(request).get("fileType"));
        requestDTO.setSubFileType(this.getParameters(request).get("subFileType"));
        log.info("图片:{}", this.getParameters(request).get("upload"));
        //requestDTO.setFileData(fileData);

        log.info("FileController upload request PARAM: requestDTO={}", requestDTO);
        Result<FileUploadResultDTO> result =  fileManagementService.uploadFile(requestDTO);
        log.info("FileController upload result={}", result);

        return this.processSuccessJSON(result);
    }
}
