package com.nicehancy.MIX.web.controller.note;

import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.service.api.model.NoteInfoDTO;
import com.nicehancy.MIX.service.api.model.request.note.*;
import com.nicehancy.MIX.service.api.note.NoteInfoService;
import com.nicehancy.MIX.web.controller.base.BaseController;
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
import java.util.List;
import java.util.UUID;

/**
 * 笔记管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/16 14:16
 **/
@Slf4j
@Controller
@RequestMapping("/note/notemanage")
public class NoteController extends BaseController {

    @Autowired
    private NoteInfoService noteInfoService;

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/page")
    public ModelAndView mainPage(){
        return new ModelAndView("note/notemanage/notepage");
    }

    /**
     * 笔记管理表单页
     * @return      表单页视图
     */
    @RequestMapping("/noteform")
    public ModelAndView noteForm(){
        return new ModelAndView("note/notemanage/noteform");
    }

    /**
     * 查询目录列表
     * @return        目录列表
     */
    @RequestMapping("/queryDirectory")
    @ResponseBody
    public ModelMap queryDirectory(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        DirectoryQueryReqDTO reqDTO = new DirectoryQueryReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setPrimaryDirectory(this.getParameters(request).get("primaryDirectory"));
        reqDTO.setSecondaryDirectory(this.getParameters(request).get("secondaryDirectory"));
        reqDTO.setRequestSystem("SYSTEM");
        reqDTO.setTraceLogId(traceLogId);
        log.info("NoteController queryDirectory request PARAM: reqDTO={}", reqDTO);
        Result<List<String>> result =  noteInfoService.queryDirectory(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap =  this.processSuccessJSON(result.getResult());
        }else{
            modelMap =  this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("NoteController queryDirectory result: {}", modelMap);
        return modelMap;
    }

    /**
     * 查询文档列表
     * @return        文档列表
     */
    @RequestMapping("/queryFileList")
    @ResponseBody
    public ModelMap queryFileList(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileListReqDTO reqDTO = new FileListReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setPrimaryDirectory(this.getParameters(request).get("primaryDirectory"));
        reqDTO.setRequestSystem("SYSTEM");
        reqDTO.setTraceLogId(traceLogId);
        log.info("NoteController queryDirectory request PARAM: reqDTO={}", reqDTO);
        Result<List<String>> result =  noteInfoService.queryFileList(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap =  this.processSuccessJSON(result.getResult());
        }else{
            modelMap =  this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("NoteController queryDirectory result: {}", modelMap);
        return modelMap;
    }

    /**
     * 查询笔记信息
     * @return        目录列表
     */
    @RequestMapping("/queryNoteInfo")
    @ResponseBody
    public ModelMap queryNoteInfo(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        NoteQueryReqDTO reqDTO = new NoteQueryReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setPrimaryDirectory(this.getParameters(request).get("primaryDirectory"));
        reqDTO.setSecondaryDirectory(this.getParameters(request).get("secondaryDirectory"));
        reqDTO.setDocumentName(this.getParameters(request).get("fileName"));
        reqDTO.setRequestSystem("SYSTEM");
        reqDTO.setTraceLogId(traceLogId);
        log.info("NoteController queryNoteInfo request PARAM: reqDTO={}", reqDTO);
        Result<List<NoteInfoDTO>> result =  noteInfoService.queryNoteInfo(reqDTO);

        ModelMap modelMap;
        if(result.isSuccess()){
            if(CollectionUtils.isEmpty(result.getResult())){
                modelMap =  this.processSuccessJSON(result.getResult());
            }else{
                modelMap = this.processSuccessJSON(result.getResult().get(0).getContent(), "success");
            }
        }else{
            modelMap =  this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("NoteController queryNoteInfo result: {}", modelMap);
        return modelMap;
    }

    /**
     * 笔记保存
     * @return     处理结果
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public ModelMap save(HttpServletRequest request){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        NoteSaveReqDTO reqDTO = new NoteSaveReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setPrimaryDirectory(this.getParameters(request).get("primaryDirectory"));
        reqDTO.setSecondaryDirectory(this.getParameters(request).get("secondaryDirectory"));
        reqDTO.setDocumentName(this.getParameters(request).get("fileName"));
        reqDTO.setContent(this.getParameters(request).get("content"));
        reqDTO.setTraceLogId(traceLogId);

        log.info("NoteController save request PARAM: reqDTO={}",reqDTO);
        Result<Boolean> result =  noteInfoService.save(reqDTO);
        ModelMap modelMap;
        if(result.getResult()) {
            modelMap =  this.processSuccessJSON(result.getResult());
        }else{
            modelMap =  this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("NoteController save result: {}", modelMap);
        return modelMap;
    }

    /**
     * 笔记管理
     * @return       分页查询数据
     */
    @RequestMapping("/manage")
    @ResponseBody
    public ModelMap manage(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);

        NoteManageReqDTO reqDTO = new NoteManageReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setPrimaryDirectory(this.getParameters(request).get("primaryDirectory"));
        reqDTO.setSecondaryDirectory(this.getParameters(request).get("secondaryDirectory"));
        reqDTO.setDocumentName(this.getParameters(request).get("documentName"));
        reqDTO.setOperatorType(this.getParameters(request).get("operateType"));
        reqDTO.setTraceLogId(traceLogId);

        log.info("NoteController manage request PARAM: reqDTO={}", reqDTO);
        Result<Boolean> result =  noteInfoService.manage(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result.getResult());
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("NoteController manage result: {}", modelMap);
        return modelMap;
    }
//    /**
//     * 表单页
//     * @return      主页视图
//     */
//    @RequestMapping("/note/updateOrAddAccount")
//    public ModelAndView accountForm(){
//        return new ModelAndView("note/account/accountForm");
//    }

}
