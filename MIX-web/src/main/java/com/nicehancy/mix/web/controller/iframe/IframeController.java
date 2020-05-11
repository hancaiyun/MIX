package com.nicehancy.mix.web.controller.iframe;

import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 嵌入第三方页面controller
 * TODO 暂无法嵌入 页面错误码500
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 15:50
 **/
@Slf4j
@Controller
@RequestMapping("/iframe")
public class IframeController extends BaseController {

    /**
     * 百度页
     * @return      页面视图
     */
    @RequestMapping("/baidu")
    public ModelAndView index(){
        return new ModelAndView("iframe/link/baidu");
    }
}
