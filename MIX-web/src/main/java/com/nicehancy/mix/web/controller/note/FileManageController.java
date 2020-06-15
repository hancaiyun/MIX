package com.nicehancy.mix.web.controller.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.enums.SendResultEnum;
import com.nicehancy.mix.service.api.file.FileManagementService;
import com.nicehancy.mix.service.api.model.request.note.FileUploadRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadRecordDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.model.result.message.MessageSendRecordDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 文件管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 14:05
 **/
@Slf4j
@Controller
public class FileManageController extends BaseController {

    @Autowired
    private FileManagementService fileManagementService;

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/note/file/list")
    public ModelAndView accountPage(){
        return new ModelAndView("note/file/list");
    }

    /**
     * 文件表单页
     * @return      表单页视图
     */
    @RequestMapping("/note/file/fileForm")
    public ModelAndView fileForm(){
        return new ModelAndView("note/file/fileform");
    }

    /**
     * 文件上传
     * @return      上传结果
     */
    @RequestMapping("/note/file/upload")
    @ResponseBody
    public ModelMap upload(){
        Result<String> result = new Result<>();
        return this.processSuccessJSON(result);
    }

    /**
     * 分页查询
     * @return      分页查询结果列表
     */
    @RequestMapping("/note/file/pageQuery")
    @ResponseBody
    public ModelMap pageQuery(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileUploadRecordPageQueryReqDTO reqDTO = new FileUploadRecordPageQueryReqDTO();
        reqDTO.setFileId(this.getParameters(request).get("fileId"));
        reqDTO.setFileName(this.getParameters(request).get("fileName"));
        reqDTO.setFileType(this.getParameters(request).get("fileType"));
        reqDTO.setCurrentPage(Integer.valueOf(this.getParameters(request).get("page")));
        reqDTO.setPageSize(Integer.valueOf(this.getParameters(request).get("limit")));
        reqDTO.setRequestSystem("SYSTEM");
        reqDTO.setTraceLogId(traceLogId);
        log.info("FileManageController pageQuery request PARAM: reqDTO={}", reqDTO);
        Result<BasePageQueryResDTO<FileUploadRecordDTO>> result =  fileManagementService.pageQuery(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            if(!CollectionUtils.isEmpty(result.getResult().getPageResult())) {
                modelMap = this.processSuccessJSON(result.getResult().getPageResult(), result.getResult().getCount());
            } else {
                modelMap = this.processSuccessJSON("查无数据");
            }
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("FileManageController pageQuery result modelMap={}", modelMap);
        return modelMap;
    }
}
