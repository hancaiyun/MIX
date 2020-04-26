package com.nicehancy.MIX.web.controller.note;

import com.nicehancy.MIX.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.UUID;

/**
 * 账户管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/30 15:54
 **/
@Slf4j
@Controller
public class AccountController extends BaseController {

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/note/account")
    public ModelAndView accountPage(){
        return new ModelAndView("note/account/account");
    }

    /**
     * 分页查询
     * @return       分页查询数据
     */
    @RequestMapping("/note/account/page")
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

        return this.processSuccessJSON("");
    }
    /**
     * 表单页
     * @return      主页视图
     */
    @RequestMapping("/note/updateOrAddAccount")
    public ModelAndView accountForm(){
        return new ModelAndView("note/account/accountForm");
    }

}
