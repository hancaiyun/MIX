package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 文件路径枚举类
 * <p>
 *     维护不同系统中的文件路径
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 12:39
 **/
@Getter
@AllArgsConstructor
public enum FilePathEnum {

    /**
     * 文件根目录
     */
    BASE_FILE_PATH("BASE_FILE_PATH", "文件根目录", "C://file/", "/usr/file/"),

    /**
     * 文件目录
     */
    FILE_PATH("FILE_PATH", "文件目录", "C://file/file_manager/", "/usr/file/file_manager/"),

    /**
     * 笔记目录
     */
    NOTE_PATH("NOTE_PATH", "笔记目录", "C://file/note_manager/", "/usr/file/note_manager/"),

    /**
     * 下载中心
     */
    DOWNLOAD_CENTER_PATH("DOWNLOAD_CENTER_PATH", "下载中心", "C://file/download_center/", "/usr/file/download_center/"),

    /**
     * 未知
     */
    UNKNOWN("","","","")
    ;

    private final String code;

    private final String desc;

    private final String pathInWindows;

    private final String pathInLinux;

    public static FilePathEnum expireOfCode(String code){
        if(!StringUtils.isEmpty(code)){
            for(FilePathEnum filePathEnum : FilePathEnum.values()){
                if(filePathEnum.getCode().equals(code)){
                    return filePathEnum;
                }
            }
        }
        return UNKNOWN;
    }
}
