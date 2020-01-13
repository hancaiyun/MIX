package com.nicehancy.MIX.web.controller.user;

import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.service.CustomUserService;
import com.nicehancy.MIX.service.api.model.UserInfoDTO;
import com.nicehancy.MIX.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 用户信息controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/2 10:03
 **/
@Slf4j
@Controller
public class UserController extends BaseController {

    @Autowired
    private CustomUserService customUserService;

    /**
     * 用户信息页面
     * @return      默认页视图
     */
    @RequestMapping("/user/info")
    public ModelAndView info(){
        return new ModelAndView("set/user/info");
    }

    /**
     * 密码修改页面
     * @return      默认页视图
     */
    @RequestMapping("/user/password")
    public ModelAndView password(){
        return new ModelAndView("set/user/password");
    }

    /**
     * 用户列表信息页面
     * @return      默认页视图
     */
    @RequestMapping("/user/list")
    public ModelAndView userList(){
        return new ModelAndView("user/user/list");
    }

    /**
     * 用户列表表单页面
     * @return      默认页视图
     */
    @RequestMapping("/user/userform")
    public ModelAndView userForm(){
        return new ModelAndView("user/user/userform");
    }

    /**
     * 后台管理用户列表页面
     * @return      默认页视图
     */
    @RequestMapping("/administrators/list")
    public ModelAndView adminList(){
        return new ModelAndView("user/administrators/list");
    }

    /**
     * 管理员角色列表页
     * @return      默认页视图
     */
    @RequestMapping("/administrators/role")
    public ModelAndView adminRole(){
        return new ModelAndView("user/administrators/role");
    }

    /**
     * 管理员表单页
     * @return      默认页视图
     */
    @RequestMapping("/administrators/adminform")
    public ModelAndView adminForm(){
        return new ModelAndView("user/administrators/adminform");
    }

    /**
     * 管理员角色表单页
     * @return      默认页视图
     */
    @RequestMapping("/administrators/roleform")
    public ModelAndView roleForm(){
        return new ModelAndView("user/administrators/roleform");
    }

    /**
     * 用户信息查询
     * @return     用户信息
     */
    @RequestMapping(value = "/set/user/info")
    @ResponseBody
    public ModelMap queryUserInfo(HttpServletRequest request){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        String userNo = this.getParameters(request).get("userNo");
        log.info("UserController queryUserInfo request PARAM: userNo={}, traceLogId={}", userNo, traceLogId);
        Result<UserInfoDTO> result =  customUserService.queryUserInfo(userNo, traceLogId);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result.getResult());
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }

        log.info("UserController queryUserInfo result={}", modelMap);
        return modelMap;
    }
}
