package com.nicehancy.MIX.web.controller;

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
        if(statusCode == 401){
            return "/401";//缺省页面
        }else if(statusCode == 404){
            return "template/tips/404";
        }else if(statusCode == 403){
            return "/403";//缺省页面
        }else{
            return "template/tips/error";
        }
    }

    @Override
    public String getErrorPath() {
        return "template/tips/error";
    }
}
