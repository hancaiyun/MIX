package com.nicehancy.mix.common.utils;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.enums.SystemTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * 系统工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 16:06
 **/
@Slf4j
public class SystemUtil {

    /**
     * 系统工具类
     */
    private SystemUtil(){
    }

    /**
     * 获取系统
     * @return           系统名
     */
    public static String getSystemType(){

        Properties properties = System.getProperties();
        String osName = properties.getProperty(CommonConstant.SYSTEM_PROPERTY);
        if(CommonConstant.SYSTEM_NAME_FOR_LINUX.equals(osName)){
            return SystemTypeEnum.LINUX.getCode();
        }
        return SystemTypeEnum.WINDOWS.getCode();
    }
}
