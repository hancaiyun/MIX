package com.nicehancy.MIX.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页面
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/29 11:25
 **/
@Controller
public class HomeController {

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
     * @return
     *
     */
    @GetMapping("/login")
    public ModelAndView login(String error){
        ModelAndView modelAndView = new ModelAndView("user/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    /**
     * 侧边栏-主页
     * @return      默认页视图
     */
    @RequestMapping("/home/homepage")
    public ModelAndView homepage(){
        return new ModelAndView("home/homepage");
    }

    /**
     * 侧边栏-控制台
     * @return      控制台视图
     */
    @RequestMapping("/home/console")
    public ModelAndView console(){
        return new ModelAndView("home/console");
    }
}
