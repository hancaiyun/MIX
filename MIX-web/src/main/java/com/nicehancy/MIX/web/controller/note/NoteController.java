package com.nicehancy.MIX.web.controller.note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 笔记管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/16 14:16
 **/
@Controller
public class NoteController {

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/note/account")
    public ModelAndView accountPage(){
        return new ModelAndView("note/account/account");
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
