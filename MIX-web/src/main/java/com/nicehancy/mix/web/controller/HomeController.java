package com.nicehancy.mix.web.controller;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.enums.AuthCodeEnum;
import com.nicehancy.mix.common.enums.SexEnum;
import com.nicehancy.mix.common.enums.SystemTypeEnum;
import com.nicehancy.mix.common.utils.SystemResourceUtil;
import com.nicehancy.mix.common.utils.SystemUtil;
import com.nicehancy.mix.service.user.CustomUserServiceImpl;
import com.nicehancy.mix.service.api.model.UserInfoDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 主页面
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/29 11:25
 **/
@Slf4j
@Controller
public class HomeController extends BaseController {

    @Autowired
    private CustomUserServiceImpl customUserService;

    /**
     * 主页
     * @return      主页视图
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 自定义登录页面
     * @param error 错误信息显示标识
     * @return      登录页
     *
     */
    @GetMapping("/login")
    public ModelAndView login(String error){
        ModelAndView modelAndView = new ModelAndView("user/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    /**
     * 用户注册页
     * @return      注册页视图
     */
    @RequestMapping("/reg")
    public ModelAndView reg(){
        return new ModelAndView("user/reg");
    }

    /**
     * 短信验证码发送
     * @return      短信验证码发送结果
     */
    @RequestMapping("/reg/vercode")
    @ResponseBody
    public ModelMap sendVercode(HttpServletRequest request) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        String phone = this.getParameters(request).get("phone");

        log.info("HomeController sendVercode request PARAM: reqDTO={}", phone);
        Result<Boolean> result = customUserService.sendVercode(phone, traceLogId);
        ModelMap modelMap;
        if(result.isSuccess()){
            if(result.getResult()) {
                modelMap = this.processSuccessJSON(result.getResult());
            }else {
                modelMap = this.processSuccessJSON("验证码发送失败， 请稍后重试");
            }
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("HomeController sendVercode modelMap={}", modelMap);
        return modelMap;
    }

    /**
     * 用户注册
     * @return      默认页视图
     */
    @RequestMapping("/reg/register")
    @ResponseBody
    public ModelMap register(HttpServletRequest request) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setLoginNo(this.getParameters(request).get("cellphone"));
        userInfoDTO.setPassword(this.getParameters(request).get("password"));
        userInfoDTO.setNickName(this.getParameters(request).get("nickname"));
        userInfoDTO.setEmail(this.getParameters(request).get("email"));
        userInfoDTO.setSex(SexEnum.MALE.getCode());
        userInfoDTO.setAuthCode(AuthCodeEnum.USER.getCode());
        userInfoDTO.setVerCode(this.getParameters(request).get("vercode"));

        log.info("HomeController register request PARAM: userInfoDTO={}, traceLogId={}", userInfoDTO, traceLogId);
        Result<Boolean> result = customUserService.register(userInfoDTO, traceLogId);
        ModelMap modelMap;
        if(result.isSuccess()){
            if(result.getResult()) {
                modelMap = this.processSuccessJSON(result.getResult());
            }else {
                modelMap = this.processSuccessJSON("用户注册失败， 请稍后重试");
            }
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("HomeController register modelMap={}", modelMap);

        return modelMap;
    }

    /**
     * 侧边栏-主页
     * @return      默认页视图
     */
    @RequestMapping("/home/home")
    public ModelAndView homepage(){
        return new ModelAndView("home/home");
    }

    /**
     * 侧边栏-控制台
     * @return      控制台视图
     */
    @RequestMapping("/home/console")
    public ModelAndView console(){
        return new ModelAndView("home/console");
    }

    /**
     * 系统信息查询
     * @return       系统资源使用
     */
    @RequestMapping("/home/console/systemInfo")
    @ResponseBody
    public ModelMap querySystemInfo(){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        //log.info("HomeController querySystemInfo request");
        Map<String, Integer> map = new HashMap<>();

        if(SystemTypeEnum.LINUX.getCode().equals(SystemUtil.getSystemType())) {
            Random random = new Random();
            Integer percent = random.nextInt(99) + 1;
            map.put("CPU", percent);
            map.put("MEMORY", 50);
            map.put("DISK", 50);
            return this.processSuccessJSON(map);
        }else{
            //CPU使用率计算时间过长，暂不通过实际计算获得， 展示一个随记数
            Random random = new Random();
            Integer percent = random.nextInt(99) + 1;
            map.put("CPU", percent);//SystemResourceUtil.getCpuRatioForWindows()
            map.put("MEMORY", SystemResourceUtil.getMemory());
            map.put("DISK", SystemResourceUtil.getTotalDisk());
            return this.processSuccessJSON(map);
        }
        //log.info("HomeController querySystemInfo result={}", modelMap);
    }


}
