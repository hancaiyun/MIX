package com.nicehancy.mix.web.controller.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.note.AccountAddReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountDeleteReqDTO;
import com.nicehancy.mix.service.api.model.request.note.AccountQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.AccountInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.note.AccountManagementService;
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

    @Autowired
    private AccountManagementService accountManagementService;

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/note/account")
    public ModelAndView accountPage(){
        return new ModelAndView("note/account/account");
    }

    /**
     * 账号表单页
     * @return      表单页视图
     */
    @RequestMapping("/note/accountForm")
    public ModelAndView accountForm(){
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
        reqDTO.setAccount(this.getParameters(request).get("account"));
        reqDTO.setCurrentPage(Integer.valueOf(this.getParameters(request).get("page")));
        reqDTO.setPageSize(Integer.valueOf(this.getParameters(request).get("limit")));

        log.info("AccountController pageQuery request PARAM: reqDTO={}", reqDTO);
        Result<BasePageQueryResDTO<AccountInfoDTO>> result = accountManagementService.pageQuery(reqDTO);
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
        log.info("AccountController pageQuery result modelMap={}", modelMap);
        return modelMap;
    }

    /**
     * 新增
     * @return       新增
     */
    @RequestMapping("/note/account/add")
    @ResponseBody
    public ModelMap add(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);

        AccountAddReqDTO reqDTO = new AccountAddReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setTraceLogId(traceLogId);
        reqDTO.setRequestSystem("MIX");
        reqDTO.setAddress(this.getParameters(request).get("address"));
        reqDTO.setAccount(this.getParameters(request).get("account"));
        reqDTO.setPassword(this.getParameters(request).get("password"));
        reqDTO.setRemark(this.getParameters(request).get("remark"));

        log.info("AccountController add request PARAM: reqDTO={}", reqDTO);
        Result<Boolean> result = accountManagementService.add(reqDTO);

        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result);
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("AccountController add result={}", modelMap);

        return modelMap;
    }

    /**
     * 删除
     * @return       删除结果
     */
    @RequestMapping("/note/account/delete")
    @ResponseBody
    public ModelMap delete(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);

        AccountDeleteReqDTO reqDTO = new AccountDeleteReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setTraceLogId(traceLogId);
        reqDTO.setRequestSystem("MIX");
        reqDTO.setId(Long.valueOf(this.getParameters(request).get("id")));

        log.info("AccountController delete request PARAM: reqDTO={}", reqDTO);
        Result<Boolean> result = accountManagementService.delete(reqDTO);

        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result);
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("AccountController delete result={}", modelMap);
        return modelMap;
    }
}
