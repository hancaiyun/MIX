package com.nicehancy.mix.web.controller.note;

import com.nicehancy.mix.service.api.model.request.note.AccountQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.AccountInfoDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     * 文件表单页
     * @return      表单页视图
     */
    @RequestMapping("/note/accountForm")
    public ModelAndView fileForm(){
        return new ModelAndView("note/account/account_form");
    }

    /**
     * 分页查询
     * @return       分页查询数据
     */
    @RequestMapping("/note/account/page")
    @ResponseBody
    public ModelMap pageQuery(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);

        AccountQueryReqDTO reqDTO = new AccountQueryReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setTraceLogId(traceLogId);
        reqDTO.setRequestSystem("MIX");
        reqDTO.setAddress(this.getParameters(request).get("address"));

        log.info("AccountController pageQuery request PARAM: reqDTO={}", reqDTO);
        List<AccountInfoDTO> list = new ArrayList<>();
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.setId("1");
        accountInfoDTO.setAddress("http://localhost:8090/login");
        accountInfoDTO.setAccount("19921577717");
        accountInfoDTO.setPassword("123456");
        accountInfoDTO.setRemark("MIX登陆");
        accountInfoDTO.setUpdatedAt(new Date());
        list.add(accountInfoDTO);

        return this.processSuccessJSON(list, 1);
    }
    /**
     * 表单页
     * @return      主页视图
     */
    @RequestMapping("/note/updateOrAddAccount")
    public ModelAndView accountForm(){
        return new ModelAndView("account_form");
    }

}
