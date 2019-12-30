package com.nicehancy.MIX.web.controller.note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 账户管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/30 15:54
 **/
@Controller
public class AccountController {

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/note/account")
    public ModelAndView accountPage(){
        return new ModelAndView("note/account/account");
    }

    /**
     * 分页查询
     * @return       分页查询数据
     */
    @RequestMapping("/note/account/page")
    public ModelAndView pageQuery(){

        return null;
    }
    /**
     * 表单页
     * @return      主页视图
     */
    @RequestMapping("/note/updateOrAddAccount")
    public ModelAndView accountForm(){
        return new ModelAndView("note/account/accountForm");
    }

}
