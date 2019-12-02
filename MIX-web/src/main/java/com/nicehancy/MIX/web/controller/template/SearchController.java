package com.nicehancy.MIX.web.controller.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 搜索controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/2 14:45
 **/
@Controller
public class SearchController {

    /**
     * 搜索页面
     * @return      默认页视图
     */
    @RequestMapping("/search")
    public ModelAndView info(){
        return new ModelAndView("/template/search");
    }
}
