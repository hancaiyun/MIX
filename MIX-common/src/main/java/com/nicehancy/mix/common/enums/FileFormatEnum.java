package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 文件格式类枚举
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/16 17:29
 **/
@Getter
@AllArgsConstructor
public enum FileFormatEnum {

    /**
     * jpg
     */
    JPG(".jpg", "PHOTO", "jpg"),

    /**
     * png
     */
    PNG(".png", "PHOTO", "png"),

    /**
     * jpeg
     */
    JPEG(".jpeg", "PHOTO", "jpeg"),

    /**
     * txt
     */
    TXT(".txt", "TEXT", "txt"),

    /**
     * doc
     */
    DOC(".doc", "WORD", "doc"),

    /**
     * docx
     */
    DOCX(".docx", "WORD", "docx"),

    /**
     * ppt
     */
    PPT(".ppt", "WORD", "ppt"),

    /**
     * pptx
     */
    PPTX(".pptx", "WORD", "pptx"),

    /**
     * xls
     */
    XLS(".xls", "WORD", "xls"),

    /**
     * xlsx
     */
    XLSX(".xlsx", "WORD", "xlsx"),

    /**
     * mp4
     */
    MP4(".mp4", "VIDEO", "mp4"),

    /**
     * avi
     */
    AVI(".avi", "VIDEO", "avi"),

    /**
     * wmv
     */
    WMV(".wmv", "VIDEO", "wmv"),

    /**
     * mpeg
     */
    MPEG(".mpeg", "VIDEO", "mpeg"),

    /**
     * dv
     */
    DV(".dv", "VIDEO", "dv"),

    /**
     * mkv
     */
    MKV(".mkv", "VIDEO", "mkv"),

    /**
     * mov
     */
    MOV(".mov", "VIDEO", "mov"),

    /**
     * mod
     */
    MOD(".mod", "VIDEO", "mod"),

    /**
     * flv
     */
    FLV(".flv", "VIDEO", "flv"),

    /**
     * 未知配置
     */
    UNKNOWN("UNKNOWN", "UNKNOWN","未知"),

    ;

    private final String code;

    private final String type;

    private final String desc;

    public static  FileFormatEnum expireOfCode(String code) {

        if (!StringUtils.isEmpty(code)) {
            for (FileFormatEnum fileFormatEnum : FileFormatEnum.values()) {
                if (fileFormatEnum.getCode().equals(code)) {
                    return fileFormatEnum;
                }
            }
        }
        return UNKNOWN;
    }
}
