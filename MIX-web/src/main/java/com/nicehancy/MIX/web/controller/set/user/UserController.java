package com.nicehancy.MIX.web.controller.set.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户信息controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/2 10:03
 **/
@Controller
public class UserController {

    /**
     * 用户信息页面
     * @return      默认页视图
     */
    @RequestMapping("/user/info")
    public ModelAndView info(){
        return new ModelAndView("/set/user/info");
    }

    /**
     * 密码修改页面
     * @return      默认页视图
     */
    @RequestMapping("/user/password")
    public ModelAndView password(){
        return new ModelAndView("/set/user/password");
    }
}
