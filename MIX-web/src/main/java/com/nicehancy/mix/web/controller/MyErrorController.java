package com.nicehancy.mix.web.controller;

import com.nicehancy.mix.common.constant.CommonConstant;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义404、500、空白页等异常页面
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/9 10:58
 **/
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == CommonConstant.STATUS_401){
            //缺省页面
            return "/401";
        }else if(statusCode == CommonConstant.STATUS_403){
            //缺省页面
            return "/403";
        }else if(statusCode == CommonConstant.STATUS_404){
            return "template/tips/404";
        }else{
            return "template/tips/error";
        }
    }

    @Override
    public String getErrorPath() {
        return "template/tips/error";
    }
}
