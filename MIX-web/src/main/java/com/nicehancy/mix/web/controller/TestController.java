package com.nicehancy.mix.web.controller;

import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/28 11:18
 **/
@Slf4j
@Controller
public class TestController extends BaseController {

    /**
     * 主页
     * @return      主页视图
     */
    @RequestMapping("/test")
    public ModelAndView index(){
        return new ModelAndView("test");
    }
}
