package com.nicehancy.mix.web.controller.component;

import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Cron在线转换工具
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/23 21:04
 **/
@Slf4j
@Controller
public class CronController extends BaseController {

    /**
     * 主页
     * @return      主页视图
     */
    @RequestMapping("/component/cronPage")
    public ModelAndView index(){
        return new ModelAndView("component/cron");
    }
}
