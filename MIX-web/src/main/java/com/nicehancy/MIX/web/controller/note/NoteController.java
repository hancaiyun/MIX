package com.nicehancy.MIX.web.controller.note;

import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.UUID;

/**
 * 笔记管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/16 14:16
 **/
@Controller
public class NoteController {

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/note/notemanage/page")
    public ModelAndView mainPage(){
        return new ModelAndView("note/notemanage/page");
    }

    /**
     * 分页查询
     * @return       分页查询数据
     */
    @RequestMapping("/note/notemanage/pagequery")
    public ModelMap pageQuery(){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
//        NoteQueryReqDTO reqDTO = new NoteQueryReqDTO();
//        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
//        reqDTO.setPrimaryDirectory(this.getParameters(request).get("primaryDirectory"));
//        reqDTO.setDocumentId(this.getParameters(request).get("documentId"));
//        reqDTO.setTraceLogId(traceLogId);

        //log.info("AccountController pageQuery request PARAM: reqDTO={}", reqDTO);

//        Result<List<NoteInfoDTO>> result =  noteService.queryNoteInfo(reqDTO);

        return null;//this.processSuccessJSON("");
    }
    /**
     * 表单页
     * @return      主页视图
     */
//    @RequestMapping("/note/updateOrAddAccount")
//    public ModelAndView accountForm(){
//        return new ModelAndView("note/account/accountForm");
//    }

}
