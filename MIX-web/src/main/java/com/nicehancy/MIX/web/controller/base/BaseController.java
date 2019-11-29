package com.nicehancy.MIX.web.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     公共请求controller
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/4/11 19:55
 **/
@Controller
@RequestMapping("/base")
public class BaseController extends AbstractController{

    //通用内容此处增加

    /**
     * 获取请求参数
     * @param request           http请求
     * @return                  map
     */
    protected Map<String,String> getParameters(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        for (Enumeration enu = request.getParameterNames(); enu.hasMoreElements(); ) {
            String key = enu.nextElement().toString();
            String val = request.getParameter(key);
            map.put(key, val);
        }
        return map;
    }
}
