package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 文件类型枚举值
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/16 10:58
 **/
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    /**
     * 照片
     */
    PHOTO("PHOTO", "照片"),

    /**
     * 文档
     */
    WORD("WORD", "文档"),

    /**
     * 文本
     */
    TEXT("TEXT", "文本"),

    /**
     * 未知配置
     */
    UNKNOWN("UNKNOWN","未知"),
    ;

    private final String code;

    private final String desc;

    public static FileTypeEnum expireOfCode(String code){
        if(!StringUtils.isEmpty(code)){
            for(FileTypeEnum fileTypeEnum : FileTypeEnum.values()){
                if(fileTypeEnum.getCode().equals(code)){
                    return fileTypeEnum;
                }
            }
        }
        return UNKNOWN;
    }
}
