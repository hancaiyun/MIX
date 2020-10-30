package com.nicehancy.mix.common.utils;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.enums.FilePathEnum;
import java.util.Properties;

/**
 * 文件路径映射工具类
 * windows系统路径、linux系统路径
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 11:24
 **/
public class FilePathMappingUtil {

    /**
     * 私有构造函数
     */
    private FilePathMappingUtil(){
    }

    /**
     * 获取windows系统跟linux系统下的文件路径
     * @param pathConstantName      路径名
     * @return                      真实路径
     */
    public static String getPath(String pathConstantName){

        Properties properties = System.getProperties();
        String osName = properties.getProperty(CommonConstant.SYSTEM_PROPERTY);
        if(CommonConstant.SYSTEM_NAME_FOR_LINUX.equals(osName)){
           return FilePathEnum.expireOfCode(pathConstantName).getPathInLinux();
        }

        return FilePathEnum.expireOfCode(pathConstantName).getPathInWindows();
    }
}
