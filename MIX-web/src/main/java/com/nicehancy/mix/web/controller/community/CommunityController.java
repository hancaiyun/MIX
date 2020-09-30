package com.nicehancy.mix.web.controller.community;

import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 社区中心controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/28 14:16
 **/
@Slf4j
@Controller
public class CommunityController extends BaseController {

    /**
     * 主页
     * @return      主页视图
     */
    @RequestMapping("/community/page")
    public ModelAndView index(){
        return new ModelAndView("community/page");
    }

    /**
     * 主页
     * @return      主页视图
     */
    @RequestMapping("/community/community")
    public ModelAndView community(){
        return new ModelAndView("community/community");
    }


    /**
     * 详情页
     * @return      详情页视图
     */
    @RequestMapping("/community/detail")
    public ModelAndView detail(){
        return new ModelAndView("community/detail");
    }
}
