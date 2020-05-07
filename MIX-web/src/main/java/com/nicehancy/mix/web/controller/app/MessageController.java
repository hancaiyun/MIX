package com.nicehancy.mix.web.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 消息中心controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/1 22:24
 **/
@Controller
public class MessageController{

    /**
     * 消息中心页面
     * @return      默认页视图
     */
    @RequestMapping("/message/page")
    public ModelAndView index(){
        return new ModelAndView("app/message/index");
    }

    /**
     * 消息中心详情页面
     * @return      默认页视图
     */
    @RequestMapping("/message/detail")
    public ModelAndView detail(){
        return new ModelAndView("app/message/detail");
    }
}
