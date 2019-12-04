package com.nicehancy.MIX.web.controller.user;

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
        return new ModelAndView("set/user/info");
    }

    /**
     * 密码修改页面
     * @return      默认页视图
     */
    @RequestMapping("/user/password")
    public ModelAndView password(){
        return new ModelAndView("set/user/password");
    }

    /**
     * 用户列表信息页面
     * @return      默认页视图
     */
    @RequestMapping("/user/list")
    public ModelAndView userList(){
        return new ModelAndView("user/user/list");
    }

    /**
     * 用户列表表单页面
     * @return      默认页视图
     */
    @RequestMapping("/user/userform")
    public ModelAndView userForm(){
        return new ModelAndView("user/user/userform");
    }

    /**
     * 后台管理用户列表页面
     * @return      默认页视图
     */
    @RequestMapping("/administrators/list")
    public ModelAndView adminList(){
        return new ModelAndView("user/administrators/list");
    }

    /**
     * 管理员角色列表页
     * @return      默认页视图
     */
    @RequestMapping("/administrators/role")
    public ModelAndView adminRole(){
        return new ModelAndView("user/administrators/role");
    }

    /**
     * 管理员表单页
     * @return      默认页视图
     */
    @RequestMapping("/administrators/adminform")
    public ModelAndView adminForm(){
        return new ModelAndView("user/administrators/adminform");
    }

    /**
     * 管理员角色表单页
     * @return      默认页视图
     */
    @RequestMapping("/administrators/roleform")
    public ModelAndView roleForm(){
        return new ModelAndView("user/administrators/roleform");
    }

}
